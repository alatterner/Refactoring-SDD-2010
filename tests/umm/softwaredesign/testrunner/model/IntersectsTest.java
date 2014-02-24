package umm.softwaredesign.testrunner.model;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import umm.softwaredesign.polygon.model.Point;
import umm.softwaredesign.polygon.model.PolygonModel;



public class IntersectsTest {
    
    @Test
    public final void intersectsTest() {
        PolygonModel poly = new PolygonModel();
        Point topLeft = new Point(0,0);
        Point topRight = new Point(10, 0);
        Point botLeft = new Point(0, 10);
        Point botRight = new Point(10,10);
        
        assertTrue("Lines do not intersect", poly.getPoly().checkIntersects(topLeft, botRight, topRight, botLeft));
    }
}
