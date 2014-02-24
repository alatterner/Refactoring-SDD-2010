package umm.softwaredesign.polygon.view.mainpanelstab;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {
    private final JFileChooser chooseFile;
    final private int returnVal;
    private String location;
    
    public FileChooser(final JFrame parent) {
        chooseFile = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Class File, Jar File, Java File", "class", "jar", "java");
        chooseFile.setFileFilter(filter); 
        location = "";
        returnVal = chooseFile.showOpenDialog(parent);
    }
    
    
    //@SuppressWarnings("PMD.SystemPrintln")
    public String openDialogBox() {
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            location = chooseFile.getSelectedFile().getAbsolutePath();
            return location;
        } else if (returnVal == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "You did not select a file!", "Warning", JOptionPane.WARNING_MESSAGE, null);
        }
        return "File location...";
    }
}
