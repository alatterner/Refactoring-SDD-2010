package umm.softwaredesign.polygon.view.tabletab;

import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import umm.softwaredesign.polygon.model.PolygonModel;

public class TablePanel extends JTable implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6125316420307428206L;
	private final static String[] COLUMNNAMES = { "Timestamp", "Seed", "Score",
		"Actual Area", "Guessed Area", "Max Guesses", "Vertices" };
	private final DefaultTableModel tableModel = new DefaultTableModel(
			COLUMNNAMES, 0);

	// DefaultTableModel dataModel = new DefaultTableModel(dataVector,
	// columnNames);

	public TablePanel(final PolygonModel polygonModel) {
		super();
		polygonModel.addObserver(this);
		setModel(tableModel);
	}

	@Override
	public void update(final Observable obs, final Object arg) {
		PolygonModel model = (PolygonModel) obs;
		if (model.getGuessedArea() == 0.0) {
			return;
		}
		Vector<String> rowVector = new Vector<String>();
		rowVector.add(generateTimestamp());
		rowVector.add(String.valueOf(model.seedForDisplay));
		rowVector.add(String.valueOf(model.getScore()));
		rowVector.add(String.valueOf(model.getPoly().getArea()));
		rowVector.add(String.valueOf(model.getGuessedArea()));
		rowVector.add(String.valueOf(model.getMaxGuesses()));
		rowVector.add(String.valueOf(model.getPoly().getNumPoints()));
		tableModel.addRow(rowVector);
	}

	public static String generateTimestamp() {
		long milliseconds = System.currentTimeMillis();
		String timestamp = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss")
		.format(milliseconds);
		return timestamp;
	}
}
