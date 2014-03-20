package umm.softwaredesign.polygon.view.displaypointstab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.util.Observable;
import java.util.Observer;
import javax.swing.text.html.*;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.DefaultCaret;
import umm.softwaredesign.polygon.model.PolygonModel;

@SuppressWarnings("serial")
public class PointsTable extends JPanel implements Observer {

	final private PolygonModel pModel;
	private JEditorPane pointsArea;
	private boolean isLight;

	public PointsTable(final PolygonModel polygonModel) {
		super();
		int width = this.getWidth();
		int height = this.getHeight();
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		this.setName("Points Table");
		pModel = polygonModel;
		pModel.addObserver(this);
		createEditorPane();
		//This makes the scrollbar stay at the top.
		DefaultCaret caret = (DefaultCaret) pointsArea.getCaret(); 
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		JScrollPane scrollPane = new JScrollPane(pointsArea);
		scrollPane.setPreferredSize(new Dimension(width, height));
		returnPoints();
		this.add(scrollPane, BorderLayout.CENTER);
	}

	private void createEditorPane() {
		pointsArea = new JEditorPane();
		pointsArea.setContentType("text/html");
		pointsArea.setBackground(Color.BLACK);
		pointsArea.setEditable(false);
		HTMLEditorKit htmlEditor = new HTMLEditorKit();
		pointsArea.setEditorKit(htmlEditor);
		pointsArea.setFont(new Font("Monospace", Font.PLAIN, 8));
	}

	private void returnPoints() {
		StringBuffer points = new StringBuffer(500000);
		points.append(changeColor());
		java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat(
				"##.#####");
		for (Point2D.Double p : pModel.getPoly().getPointList()) {
			points.append("Point: [").append(decimalFormat.format(p.getX()))
					.append(", ").append(decimalFormat.format(p.getY()))
					.append("]<br />");
		}
		points.append("<b>Guessed Points:</b><br /><font color=#0098FF><b> ")
				.append(getPercentage())
				.append("% Correct</b>" + "</font><br />");
		for (Point2D.Double p : pModel.getGuessedPoints()) {
			if (pModel.getCorrect().contains(p)) {
				points.append(
						"<font color=#0098FF size=3 face=Monospace>Point: [")
						.append(decimalFormat.format(p.getX())).append(", ")
						.append(decimalFormat.format(p.getY()))
						.append("]</font><br />");
			} else {
				points.append(
						"<font color=#E42217 size=3 face=Monospace>Point: [")
						.append(decimalFormat.format(p.getX())).append(", ")
						.append(decimalFormat.format(p.getY()))
						.append("]</font><br />");
			}
		}
		points.append("</html>");
		pointsArea.setText(points.toString());
	}

	private Object changeColor() {
		String textColor = "<html><font color=#FFFFFF size=3 face=Monospace><b>Polygon Points:</b> <br />";
		pointsArea.setBackground(Color.BLACK);
		if (isLight) {
			textColor = "<html><font color=#000000 size=3 face=Monospace><b>Polygon Points:</b> <br />";
			pointsArea.setBackground(Color.WHITE);
		}

		return textColor;
	}

	private double getPercentage() {
		return ((double) pModel.getCorrect().size()
				/ (double) pModel.getGuessedPoints().size() * 100.0);
	}

	@Override
	public void update(final Observable observable, final Object arg) {
		returnPoints();
	}

	public void lightColorScheme(final boolean lightOn) {
		isLight = lightOn;
		returnPoints();

	}

}
