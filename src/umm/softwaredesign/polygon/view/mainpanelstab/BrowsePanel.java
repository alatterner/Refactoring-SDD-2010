package umm.softwaredesign.polygon.view.mainpanelstab;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import umm.softwaredesign.polygon.controllers.FileChooserController;
import umm.softwaredesign.polygon.controllers.RunController;
import umm.softwaredesign.polygon.model.PolygonModel;

@SuppressWarnings("serial")
public class BrowsePanel extends JPanel {
    
    final private JTextField fileTextField;
    final private PolygonModel pModel;
    
    public BrowsePanel(final PolygonModel polygonModel) {
        super();
        this.setName("Top Panel");
        pModel = polygonModel;
        JLabel file = new JLabel("File: ");
        fileTextField = new JTextField(20);
        fileTextField.setName("File Field");
        fileTextField.setText("File Location...");
        fileTextField.setToolTipText("Enter the file location here, or press Select File button.");
        this.setPreferredSize(new Dimension(550, 50));
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        this.add(file, BorderLayout.WEST);
        this.add(fileTextField, BorderLayout.WEST);
        createButtons();
        this.setVisible(true);}

    private void createButtons() {
        FileChooserController fileChooser = new FileChooserController((JFrame)this.getParent(), fileTextField);
        JButton fileButton = new JButton("Select File");
        fileButton.addActionListener(fileChooser);
        JButton runButton = new JButton("Run");
        runButton.setToolTipText("Runs the selected file.");
        runButton.addActionListener(new RunController(fileTextField, pModel));
        this.add(fileButton, BorderLayout.CENTER);    
        this.add(runButton, BorderLayout.EAST);
    }
    
    
}
