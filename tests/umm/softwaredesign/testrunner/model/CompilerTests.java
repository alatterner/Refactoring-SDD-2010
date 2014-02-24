package umm.softwaredesign.testrunner.model;
import org.junit.Test;

import umm.softwaredesign.polygon.controllers.CheckAndCompileFile;
import umm.softwaredesign.polygon.controllers.FileToRun;
import umm.softwaredesign.polygon.model.PolygonModel;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class CompilerTests {
        @Test
        public final void testReturnFileExtension() {
                final File file = new File("resources/test.txt");
                final String[] testNameAndExt = CheckAndCompileFile.getFileNameAndExt(file);
                assertEquals("File name is \"test.txt\"", "test.txt", file.getName());
                assertEquals("File name (sans extension) should be \"test\"", "test", testNameAndExt[0]);
                assertEquals("File extension should be \"txt\"", "txt", testNameAndExt[1]);
        }
        
    @Test(expected = IOException.class)
    public void wrongExtensionFails() throws IOException, ClassNotFoundException, SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        final File file = new File("resources/test.txt");
        final PolygonModel model = new PolygonModel();
        FileToRun txtFile = new FileToRun(file, model);
        txtFile.runFileOnTestCase();
    }

    
    @Test
    public void testCompiledFile() throws IOException, ClassNotFoundException, SecurityException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final File file = new File("tests/PolygonArea.java");
        PolygonModel model = new PolygonModel();
        model.createRandomPolygon(55116, 15, 200);
        FileToRun compiledFile = new FileToRun(file, model);
        double score = compiledFile.runFileOnTestCase();
        assertTrue("OH NO!", score<=5);
    }
    
//    @Test
//    public void compileAFileFail() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException {
//      final File file = new File("resources/FailingCode.java");
//      PolygonModel model = new PolygonModel();
//      model.createRandomPolygon(55116, 15, 200);
//      FileToRun compiledFile = new FileToRun(file, model);
//      double score = compiledFile.runFileOnTestCase();
//    }
}