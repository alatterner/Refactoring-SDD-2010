package umm.softwaredesign.polygon.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

public class ClearConsoleController implements ActionListener {

    final private JTextArea console;
    
    public ClearConsoleController(final JTextArea theConsole) {
        console = theConsole;
    }
    
    @Override
    public void actionPerformed(final ActionEvent event) {
        console.setText("");
    }

    /**
     * I made this for testing purposes.
     */
    public String getText(){
        return console.getText();
    }
}
