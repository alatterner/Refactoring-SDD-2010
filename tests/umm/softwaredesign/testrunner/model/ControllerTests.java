package umm.softwaredesign.testrunner.model;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

import javax.swing.JTextArea;

import org.junit.Test;

import umm.softwaredesign.polygon.controllers.ClearConsoleController;
import umm.softwaredesign.polygon.controllers.ColorSchemeController;

public class ControllerTests {
    
    @Test
    public final void clearConsoleControllerTest() {
        JTextArea testTextArea = new JTextArea();
        testTextArea.setText("A string goes here");
    ClearConsoleController testClearConsCont = new ClearConsoleController(testTextArea);
    assertTrue("The string was lost in the construction of the Controller", testClearConsCont.getText().equals("A string goes here"));
    testClearConsCont.actionPerformed(null);
    assertTrue("The string was not cleared", testClearConsCont.getText().equals(""));
    }

}
