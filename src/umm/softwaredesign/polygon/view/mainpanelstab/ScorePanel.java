package umm.softwaredesign.polygon.view.mainpanelstab;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import umm.softwaredesign.polygon.model.PolygonModel;

@SuppressWarnings("serial")
public class ScorePanel extends JPanel implements Observer {

    private JLabel score;
    private JLabel guessedArea;
    private JLabel actualArea;
    final private PolygonModel pModel;
    
    public ScorePanel(final PolygonModel polygonModel) {
        super();
        this.setName("Score Panel");
        pModel = polygonModel;
        pModel.addObserver(this);
        initializeFields();
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        this.setPreferredSize(new Dimension(250, 100));
        this.setLayout(new GridLayout(3, 1));
        this.add(guessedArea);
        this.add(actualArea);
        this.add(score);
    }

    private void initializeFields() {
        guessedArea = new JLabel("Guessed Area: ");
        actualArea = new JLabel("Actual Area: ");
        actualArea.setName("Actual Area");
        guessedArea.setName("Guessed Area");
        score = new JLabel("SCORE: ");
        score.setName("Score Box");
    }
    
    @Override
    public void update(final Observable observable, final Object arg) {
        guessedArea.setText(String.format("<html>Guessed Area:<P> %.4f </html>",pModel.getGuessedArea()));
        actualArea.setText(String.format("<html>Actual Area:<P> %.4f </html>",pModel.getPoly().getArea()));
        score.setText(String.format("<html>SCORE: <P><font color=#ff0000> %.4f </font></html>", pModel.getScore()));
    }

}
