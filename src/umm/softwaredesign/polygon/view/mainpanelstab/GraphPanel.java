package umm.softwaredesign.polygon.view.mainpanelstab;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import umm.softwaredesign.polygon.model.PolygonModel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("serial")
public class GraphPanel extends JPanel implements Observer {
    private int width;
    private int height;
    final private PolygonModel polygonModel;
    private Graphics2D g2d;
    private boolean isLight = false;
    
    public GraphPanel(final PolygonModel polyModel) {
        super();
        this.setName("Graph Panel");
        polygonModel = polyModel;
        polygonModel.addObserver(this);
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    }
    
    public void lightColorScheme(final boolean light) {
		isLight = light;
		
	}
    @Override
    public void paintComponent(final Graphics graphics) {
    	width = this.getWidth();
        height = this.getHeight();
    	changeColor(graphics);
        super.paintComponent(graphics);
        g2d = (Graphics2D) graphics;
        
        //g2d.drawRect(0, 0, (this.getWidth()-3), (this.getHeight())-3);
        drawGrid();
        drawGeneratedPolygon(graphics); 
        drawGuessed(graphics);
    }
    public void changeColor(final Graphics graphic){
    	if (isLight) {
    		this.setBackground(Color.white);
    		graphic.setColor(Color.black);
    	}else {
    		this.setBackground(Color.black);
    		graphic.setColor(Color.white);
    	}
    }
    private void drawGrid(){
        g2d.setColor(Color.gray);
        for(int i = 1; i < 11; i++){
        g2d.drawLine(0, height*i/10, this.getWidth(), height*i/10);
        }
        for(int i = 1; i < 11; i++){
            g2d.drawLine(width*i/10, 0, width*i/10,height );
        }
    }
    public void drawGeneratedPolygon(final Graphics graphic) {
        java.util.List<Point2D.Double> pointList = polygonModel.getPoly().getPointList();
        int points = polygonModel.getPoly().getNumPoints();
        changeColor(graphic);
        for (int i = 0; i < points; i++) {
            graphic.drawLine((int) (pointList.get(i).getX()*(width/10)), 
                       (int) (pointList.get(i).getY()*(height/10)), 
                       (int) (pointList.get((i+1) % points).getX()*(width/10)), 
                       (int) (pointList.get((i+1) % points).getY()*(height/10)));
        }   
        for (Point2D.Double p : pointList) {
            graphic.fillOval((int) (p.getX()*(width/10)) - 2, (int) (p.getY()*(height/10)) - 2, 4, 4);
        }
    }

    public void drawGuessed(final Graphics graphic) {
        java.util.List<Point2D.Double> incorrectPoints = polygonModel.getIncorrect();
        java.util.List<Point2D.Double> correctPoints = polygonModel.getCorrect();
        graphic.setColor(new Color(0, 152, 255));
        for (Point2D.Double correct : correctPoints) {
            graphic.fillOval((int) (correct.getX()*(width/10)) - 2, (int) (correct.getY()*(height/10)) - 2, 4, 4);
        }
        
        graphic.setColor(new Color(229, 29, 23));
        for (Point2D.Double incorrect : incorrectPoints) {
            graphic.fillOval((int) (incorrect.getX()*(width/10)) - 2, (int) (incorrect.getY()*(height/10)) - 2,4,4);
        }
    }

    @Override
    public void update(final Observable observable, final Object arg) {
       repaint();
    }

	
}
