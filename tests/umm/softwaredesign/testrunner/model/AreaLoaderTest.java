package umm.softwaredesign.testrunner.model;


import static org.junit.Assert.assertTrue;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import org.junit.Test;

import polygonarea.PolygonAreaIF;
import umm.softwaredesign.polygon.controllers.StudentsClassLoader;
import umm.softwaredesign.polygon.model.PolygonAreaLoader;
import umm.softwaredesign.polygon.model.PolygonModel;

public class AreaLoaderTest {
    
    @Test
    public void testCreateAreaLoader() throws MalformedURLException, ClassNotFoundException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        PolygonModel testModel = new PolygonModel();
        String fileName = "resources/PolygonArea.class";
        StudentsClassLoader testLoader = new StudentsClassLoader(fileName, testModel);
        assertTrue("Did not get an instance of PolygonArea", testLoader.getInstance() instanceof PolygonAreaIF);
    }
}
