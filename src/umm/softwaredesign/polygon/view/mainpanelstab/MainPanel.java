package umm.softwaredesign.polygon.view.mainpanelstab;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import umm.softwaredesign.polygon.controllers.ColorSchemeController;
import umm.softwaredesign.polygon.model.PolygonModel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel{

	private final GraphPanel gPanel;
	
    public MainPanel(final PolygonModel polygonModel, final ColorSchemeController colorControl) {
    	super();
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        this.setName("Main Panel");
        this.setSize(500, 550);
        ConsolePanel consolePanel = new ConsolePanel();
        BrowsePanel tPanel = new BrowsePanel(polygonModel);
        gPanel = new GraphPanel(polygonModel);
        SidePanel sPanel = new SidePanel(polygonModel, consolePanel, colorControl);
        this.add(tPanel, BorderLayout.NORTH);
        this.add(sPanel, BorderLayout.WEST);
        this.add(gPanel, BorderLayout.CENTER);
        this.add(consolePanel, BorderLayout.SOUTH);
    }

	public void lightColorScheme(final boolean isLight) {
		gPanel.lightColorScheme(isLight);
		gPanel.repaint();
	}

}