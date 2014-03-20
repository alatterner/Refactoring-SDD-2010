package umm.softwaredesign.testrunner.model;

import static org.junit.Assert.assertTrue;

import java.awt.geom.Point2D;

import org.junit.Test;

import umm.softwaredesign.polygon.model.PolygonModel;



public class IntersectsTest {
    
    @Test
    public final void intersectsTest() {
        PolygonModel poly = new PolygonModel();
        Point2D.Double topLeft = new Point2D.Double(0,0);
        Point2D.Double topRight = new Point2D.Double(10, 0);
        Point2D.Double botLeft = new Point2D.Double(0, 10);
        Point2D.Double botRight = new Point2D.Double(10,10);
        
        assertTrue("Lines do not intersect", poly.getPoly().checkIntersects(topLeft, botRight, topRight, botLeft));
    }
}
