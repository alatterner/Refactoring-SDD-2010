package umm.softwaredesign.polygon.view.displaypointstab;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import umm.softwaredesign.polygon.model.PolygonModel;

@SuppressWarnings("serial")
public class PointsPanel extends JPanel {
    
	private final PointsTable pTable;
	
    public PointsPanel(final PolygonModel polygonModel) {
	super();
	pTable = new PointsTable(polygonModel);
	BorderLayout layout = new BorderLayout();
	this.setLayout(layout);
	this.add(pTable);
    this.setName("Points Panel");
    }

	public void lightColorScheme(final boolean isLight) {
		pTable.lightColorScheme(isLight);
		
	}
}
