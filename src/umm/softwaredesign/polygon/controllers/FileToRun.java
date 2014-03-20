package umm.softwaredesign.polygon.controllers;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import polygonarea.PolygonAreaIF;
import umm.softwaredesign.polygon.model.*;

public class FileToRun {
	final private PolygonModel pModel;
	final private StudentsClassLoader loader;

	public FileToRun(final File file, final PolygonModel model)
			throws ClassNotFoundException, IOException {
		File studentFile;
		studentFile = file;
		pModel = model;
		loader = CheckAndCompileFile.compileFileAndReturnLoader(model,
				studentFile);
	}

	public double runFileOnTestCase() throws SecurityException,
			IllegalArgumentException, NoSuchMethodException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {

		// this is the line that needs to be threaded to check for hanging
		PolygonAreaIF testCode = (PolygonAreaIF) loader.getInstance();
		final double polygonAreaEstimate = testCode.estimate(pModel
				.getMaxGuesses());
		pModel.scoreGuessedArea(polygonAreaEstimate);
		return pModel.getScore();
	}

}