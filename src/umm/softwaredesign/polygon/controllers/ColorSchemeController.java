package umm.softwaredesign.polygon.controllers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import umm.softwaredesign.polygon.view.displaypointstab.PointsPanel;
import umm.softwaredesign.polygon.view.mainpanelstab.MainPanel;

public class ColorSchemeController implements ItemListener{
	
	private MainPanel main;
	private PointsPanel points;
	
	public void addPanels(final MainPanel mainPanel, final PointsPanel pointsPanel) {
		points = pointsPanel;
		main = mainPanel;
	}

	@Override
	public void itemStateChanged(final ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			main.lightColorScheme(true);
			points.lightColorScheme(true);
		} else if (event.getStateChange() == ItemEvent.DESELECTED) {
			main.lightColorScheme(false);
			points.lightColorScheme(false);
		}

	}
}
