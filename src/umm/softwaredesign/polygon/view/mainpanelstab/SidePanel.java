package umm.softwaredesign.polygon.view.mainpanelstab;

import java.awt.GridLayout;

import javax.swing.JPanel;

import umm.softwaredesign.polygon.controllers.ColorSchemeController;
import umm.softwaredesign.polygon.model.PolygonModel;

@SuppressWarnings("serial")
public class SidePanel extends JPanel {

    
    public SidePanel(final PolygonModel polygonModel, final ConsolePanel consolePanel, final ColorSchemeController colorControl) {
        super();
        this.setName("Side Panel");
        ScorePanel scorePanel = new ScorePanel(polygonModel);
        GeneratePolygonPanel generatePanel = new GeneratePolygonPanel(polygonModel, consolePanel, colorControl);
        this.setLayout(new GridLayout(2, 1));
        this.add(generatePanel);
        this.add(scorePanel);       
    }
    
}
