package umm.softwaredesign.polygon.view;


import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import umm.softwaredesign.polygon.controllers.ColorSchemeController;
import umm.softwaredesign.polygon.model.PolygonModel;
import umm.softwaredesign.polygon.view.displaypointstab.PointsPanel;
import umm.softwaredesign.polygon.view.mainpanelstab.MainPanel;
import umm.softwaredesign.polygon.view.tabletab.TablePanel;

@SuppressWarnings("serial")
public class TabsPanel extends JTabbedPane {
    
    public TabsPanel(final PolygonModel polygonModel) {
    	super();
    	ColorSchemeController colorControl = new ColorSchemeController();
    	MainPanel main = new MainPanel(polygonModel, colorControl);
    	PointsPanel points = new PointsPanel(polygonModel); //Guessed Points tab
        JScrollPane tableScrollPane = initializeTablePanel(polygonModel);
        colorControl.addPanels(main, points);
        this.addTab("Main", main);
        this.addTab("Display Points", points); 
        this.addTab("Table", tableScrollPane);
    }

    private JScrollPane initializeTablePanel(final PolygonModel polygonModel) {
        TablePanel table = new TablePanel(polygonModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        return tableScrollPane;
    }
    
    
}
