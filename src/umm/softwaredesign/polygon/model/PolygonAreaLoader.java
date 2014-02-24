package umm.softwaredesign.polygon.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import polygonarea.PolygonIF;


public class PolygonAreaLoader {

    private Class<?> polygonArea;
    final private PolygonModel pModel;

    public PolygonAreaLoader(final String filename, final PolygonModel polygonModel) throws MalformedURLException, ClassNotFoundException {
        pModel = polygonModel;
        loadClass(filename);

    }
    
    private void loadClass(final String filename) throws MalformedURLException, ClassNotFoundException {
            URL[] fileURLArray = {new URL("file:" + filename)};
            URLClassLoader urlClassLoader = new URLClassLoader(fileURLArray);
            polygonArea = urlClassLoader.loadClass("PolygonArea");  
       
    }

    public Object getInstance() throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
           Constructor<?> constructor = polygonArea.getConstructor(PolygonIF.class);
       
          return constructor.newInstance(pModel);
    }

}
