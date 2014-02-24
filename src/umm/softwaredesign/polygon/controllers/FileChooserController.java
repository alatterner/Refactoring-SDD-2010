package umm.softwaredesign.polygon.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;

import umm.softwaredesign.polygon.view.mainpanelstab.FileChooser;

public class FileChooserController implements ActionListener {

    private final JFrame parent;
    private final JTextField textField;
    
    public FileChooserController(final JFrame frame, final JTextField field) {
        parent = frame;
        textField = field;
    }
    
    @Override
    public void actionPerformed(final ActionEvent event) {
        FileChooser fileChooser = new FileChooser(parent);
        String fileLocation = fileChooser.openDialogBox();
        textField.setText(fileLocation);
        
    }

}
