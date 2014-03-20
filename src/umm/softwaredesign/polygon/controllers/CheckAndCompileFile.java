package umm.softwaredesign.polygon.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import umm.softwaredesign.polygon.model.*;

public final class CheckAndCompileFile {
	private final static Logger LOGGER = Logger
			.getLogger("model.CheckAndCompileFile");

	private CheckAndCompileFile() {
		/*
		 * This is a private constructor because all of the methods in this
		 * class are static
		 */
	}

	public static StudentsClassLoader compileFileAndReturnLoader(
			final PolygonModel model, final File studentFile)
			throws IOException, ClassNotFoundException {
		final String[] fileNameAndExt = getFileNameAndExt(studentFile);
		if (fileNameAndExt.length == 1) {
			LOGGER.severe("File handed to checkFiletype wasn't .java or class, throwihng IOException");
			throw new IOException(
					"Given file did not have a .java or .class extension and thus is unsupported.");
		}
		File compiledFile;
		if ("java".equals(fileNameAndExt[1])) {
			compileFile(studentFile);
			compiledFile = new File(studentFile.getParentFile() + "/"
					+ fileNameAndExt[0] + ".class");
		} else if ("class".equals(fileNameAndExt[1])) {
			compiledFile = studentFile;
		} else {
			LOGGER.severe("File handed to checkFiletype wasn't .java or .class, throwing IOException");
			throw new IOException(
					"Given file did not have a .java or .class extension and thus is unsupported.");
		}
		return new StudentsClassLoader(compiledFile.getPath(), model);
	}

	public static String[] getFileNameAndExt(final File inputFile) {
		final String[] splitFileName = inputFile.getName().split("\\.");
		return splitFileName;
	}

	protected static File compileFile(final File inputFile) throws IOException {
		final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		final StandardJavaFileManager fileManager = compiler
				.getStandardFileManager(null, null, null);
		// prepare the source file(s) to compile
		final List<File> sourceFileList = new ArrayList<File>();
		sourceFileList.add(new File(inputFile.getPath()));
		if (!compiler.getTask(null, fileManager, null, null, null,
				fileManager.getJavaFileObjectsFromFiles(sourceFileList)).call()) {
			LOGGER.severe("Something bad happened when we tried to compile a .java file.");
		}
		fileManager.close();
		return inputFile;
	}
}