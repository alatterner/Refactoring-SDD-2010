package umm.softwaredesign.polygon.view;



import java.awt.Dimension;

import javax.swing.JFrame;
import umm.softwaredesign.polygon.model.PolygonModel;

@SuppressWarnings("serial")
public class PolygonFrame extends JFrame {
    
    public PolygonFrame() {
        super();
        this.setMinimumSize(new Dimension(545, 615));
        PolygonModel polygonModel = new PolygonModel();
        TabsPanel tabsPanel = new TabsPanel(polygonModel); 
        getContentPane().add(tabsPanel);
        polygonModel.createRandomPolygon();
        this.setSize(700,700);
        this.setResizable(true);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
