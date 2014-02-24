package umm.softwaredesign.testrunner.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import umm.softwaredesign.polygon.model.Point;
import umm.softwaredesign.polygon.model.Polygon;
import umm.softwaredesign.polygon.model.PolygonModel;



public class ContainsTest {
    @Test
    public final void containsTest() {
        PolygonModel model = new PolygonModel();
        Polygon polygon = model.getPoly();
        polygon.addInitialPoint(new Point(2, 2));
        polygon.addInitialPoint(new Point(7, 2));
        polygon.addInitialPoint(new Point(7, 7));
        polygon.addInitialPoint(new Point(2, 7));
        assertTrue("Polygon doesn't contain the point", polygon.contains(new Point(5,5)));
        assertFalse("Polygon does contain the point", polygon.contains(new Point(10, 10)));
    }
}
