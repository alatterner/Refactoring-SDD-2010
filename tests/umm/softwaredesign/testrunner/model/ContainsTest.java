package umm.softwaredesign.testrunner.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.geom.Point2D;

import org.junit.Test;

import umm.softwaredesign.polygon.model.Polygon;
import umm.softwaredesign.polygon.model.PolygonModel;



public class ContainsTest {
    @Test
    public final void containsTest() {
        PolygonModel model = new PolygonModel();
        Polygon polygon = model.getPoly();
        polygon.addInitialPoint(new Point2D.Double(2, 2));
        polygon.addInitialPoint(new Point2D.Double(7, 2));
        polygon.addInitialPoint(new Point2D.Double(7, 7));
        polygon.addInitialPoint(new Point2D.Double(2, 7));
        assertTrue("Polygon doesn't contain the point", polygon.contains(new Point2D.Double(5,5)));
        assertFalse("Polygon does contain the point", polygon.contains(new Point2D.Double(10, 10)));
    }
}
