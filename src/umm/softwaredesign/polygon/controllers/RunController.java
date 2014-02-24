package umm.softwaredesign.polygon.controllers;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import polygonarea.PolygonAreaIF;
import umm.softwaredesign.polygon.model.PolygonModel;
import umm.softwaredesign.polygon.model.TooManyGuessesException;


public class RunController implements ActionListener {

    private final PolygonModel pModel;
    private String file;
    private final JTextField textField;
    

    public RunController(final JTextField fileName,
            final PolygonModel polygonModel) {
        pModel = polygonModel;
        textField = fileName;
        file = textField.getText();
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
            pModel.clear();
            extractPath();
                runClass();
    }

    private void runClass() throws SecurityException {
        try {
            StudentsClassLoader areaLoader = null;
                File stuFile = new File(file);
                areaLoader = CheckAndCompileFile.compileFileAndReturnLoader(pModel, stuFile);
            areaLoader = new StudentsClassLoader(file, pModel);
            PolygonAreaIF polygonArea = (PolygonAreaIF) areaLoader.getInstance();
            double area = polygonArea.estimate(pModel.getMaxGuesses());
            pModel.scoreGuessedArea(area);
        } catch (ClassFormatError e) {
            dialogueBox("The class is formatted incorrectly!/n Your file could not be compiled.", "Class Format Error!");
        } catch (MalformedURLException e) {
            dialogueBox("The file path is incorrectly formatted!", "Malformed URL!");
        } catch (ClassNotFoundException e) {
            dialogueBox("Your class must be named PolygonArea", "Class Not Found!");
        } catch (IllegalArgumentException e) {
            dialogueBox("Make sure your method takes in only an integer!", "Incorrect Arguments");
        } catch (InstantiationException e) {
            dialogueBox("Test Instantiation.", "Error!");
        } catch (IllegalAccessException e) {
            dialogueBox("Make sure your estimate method is public", "Estimate not accessable");
        } catch (InvocationTargetException e) {
            dialogueBox(e.getMessage(), "Your code has thrown an error.");
        } catch (NoSuchMethodException e) {
            dialogueBox("Make sure your method is named estimate!", "No Method called Estimate!");
        } catch (NoClassDefFoundError e) {
            dialogueBox("The class is formatted incorrectly!", "Class Format Error!");
        } catch (TooManyGuessesException e) {
            dialogueBox("You made too many guesses!", "Too Many Guesses!");
        } catch (SecurityException e){
            dialogueBox("You may have tried to access files outside of the project.", "Illegal Operation");
        } catch (IOException e){
            dialogueBox(".java file could not compile", "Compiler Exception");
        }
    }
    
    private void dialogueBox(final String message, final String title){
        JOptionPane.showMessageDialog(null, message,title, 
                JOptionPane.ERROR_MESSAGE, null);
    }

    private void extractPath() {
            file = textField.getText();
    }
}
