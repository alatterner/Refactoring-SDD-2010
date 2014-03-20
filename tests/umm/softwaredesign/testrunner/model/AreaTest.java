package umm.softwaredesign.testrunner.model;

import static org.junit.Assert.assertEquals;

import java.awt.geom.Point2D;

import org.junit.Test;

import umm.softwaredesign.polygon.model.Polygon;
import umm.softwaredesign.polygon.model.PolygonModel;


public class AreaTest {
    @Test
    public void areaTest() {
        PolygonModel pModel = new PolygonModel();
        Polygon polygon = pModel.getPoly();
        polygon.addInitialPoint(new Point2D.Double(2, 2));
        polygon.addInitialPoint(new Point2D.Double(7, 2));
        polygon.addInitialPoint(new Point2D.Double(7, 7));
        polygon.addInitialPoint(new Point2D.Double(2, 7));
        assertEquals("Area incorrect", 25.0, polygon.getArea(), .01);
    }
}
