package umm.softwaredesign.polygon.view.mainpanelstab;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import umm.softwaredesign.polygon.controllers.ClearConsoleController;
import umm.softwaredesign.polygon.controllers.GeneratePolygonController;
import umm.softwaredesign.polygon.controllers.ColorSchemeController;
import umm.softwaredesign.polygon.model.PolygonModel;

@SuppressWarnings("serial")
public class GeneratePolygonPanel extends JPanel implements Observer {
    
    final private JTextField guessesField;
    private JTextField seedField;
    final private PolygonModel pModel;
    final private JTextField verticesField;
    
    public GeneratePolygonPanel(final PolygonModel polygonModel, final ConsolePanel consolePanel, final ColorSchemeController colorSchemeController) {
	super();
	this.setName("GeneratePolygon Panel");
	pModel = polygonModel;
	pModel.addObserver(this);
	guessesField = new JTextField(3);
	guessesField.setName("Guesses Field");
	verticesField = new JTextField(2);
	verticesField.setName("Vertices Field");
	final JLabel guessesLabel = new JLabel("Guesses:");
	final JLabel verticesLabel = new JLabel("Vertices:");
	guessesLabel.setText("Guesses: ");
	verticesLabel.setText("Vertices: ");
	verticesField.setText("Vertices: " + pModel.getPoly().getNumPoints());
	this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	this.setPreferredSize(new Dimension(200, 100));
	this.add(guessesLabel, BorderLayout.NORTH);
	this.add(guessesField, BorderLayout.NORTH);
	this.add(verticesLabel, BorderLayout.CENTER);
	this.add(verticesField, BorderLayout.CENTER);
	createSeedLabelAndTextField();
	createRadioColorButton(colorSchemeController);
	createButtons(consolePanel);
    }
    
    private void createRadioColorButton(final ColorSchemeController colorControl) {
		final JRadioButton colorButton = new JRadioButton("Light Graph");
		colorButton.setName("colorButton");
		colorButton.setActionCommand("light");
		colorButton.addItemListener(colorControl);
		this.add(colorButton);
		
	}

    private void createSeedLabelAndTextField() {
        seedField = new JTextField(8);
        seedField.setName("Seed Field");
        JLabel seed = new JLabel("Seed: ");
        seedField.setText("936");
        seedField.setToolTipText("Enter a seed number for reptition.");
        this.add(seed, BorderLayout.WEST);
        this.add(seedField, BorderLayout.WEST);
    }

    private void createButtons(final ConsolePanel consolePanel) {
        GeneratePolygonController polygonController = new GeneratePolygonController(pModel, seedField, guessesField, verticesField);
        ClearConsoleController clearConsoleController = new ClearConsoleController(consolePanel.consoleOutput);
        
        JButton clearConsole = new JButton("Clear Console");
        clearConsole.addActionListener(clearConsoleController);
        JButton random = new JButton("Random Polygon");
        random.addActionListener(polygonController);
        JButton randomSeed = new JButton("Seeded Polygon");
        randomSeed.addActionListener(polygonController);
        this.add(randomSeed, BorderLayout.SOUTH);
        this.add(random, BorderLayout.SOUTH);
        this.add(clearConsole, BorderLayout.SOUTH);
    }

    @Override
    public void update(final Observable observer, final Object arg) {
        guessesField.setText(String.valueOf(pModel.getMaxGuesses()));
        verticesField.setText(String.valueOf(pModel.getPoly().getNumPoints()));
        seedField.setText(String.valueOf(pModel.seedForDisplay));
    }
}
