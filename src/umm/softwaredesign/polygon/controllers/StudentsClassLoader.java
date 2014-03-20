package umm.softwaredesign.polygon.controllers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import polygonarea.PolygonIF;

import umm.softwaredesign.polygon.model.PolygonModel;

public class StudentsClassLoader {

	private static final String CLASS_NAME = "PolygonArea";
	private Class<?> polygonArea;
	final private PolygonModel pModel;

	public StudentsClassLoader(final String filename,
			final PolygonModel polygonModel) throws MalformedURLException,
			ClassNotFoundException {
		pModel = polygonModel;
		loadClass(filename);

	}

	private void loadClass(final String filename) throws MalformedURLException,
			ClassNotFoundException {
		String editedFileName = "";
		String[] stringArray = filename.split("/");
		for (int i = 0; i < stringArray.length - 1; i++) {
			editedFileName += stringArray[i].concat("/");
		}

		URL[] fileURLArray = { new URL("file:" + editedFileName) };
		URLClassLoader urlClassLoader = new URLClassLoader(fileURLArray);
		polygonArea = urlClassLoader.loadClass(CLASS_NAME);
	}

	public Object getInstance() throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {
		Constructor<?> constructor = polygonArea
				.getConstructor(PolygonIF.class);
		return constructor.newInstance(pModel);
	}

}
