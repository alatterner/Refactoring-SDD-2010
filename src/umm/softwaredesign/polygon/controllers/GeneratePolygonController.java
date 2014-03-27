package umm.softwaredesign.polygon.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import umm.softwaredesign.polygon.model.PolygonModel;

public class GeneratePolygonController implements ActionListener {

	final private PolygonModel pModel;
	final private JTextField seedNumber;
	final private JTextField sidesField;
	final private JTextField guessesField;

	public GeneratePolygonController(final PolygonModel polygonModel,
			final JTextField seed, final JTextField guesses,
			final JTextField sides) {
		seedNumber = seed;
		sidesField = sides;
		guessesField = guesses;
		pModel = polygonModel;
	}

	@Override
	public void actionPerformed(final ActionEvent event) {
		if (((JButton) event.getSource()).getText().equals("Random Polygon")) {
			pModel.createRandomPolygon();
			pModel.seedForDisplay = Integer.parseInt(seedNumber.getText());
		} else {
			try {
				int seed = Integer.parseInt(seedNumber.getText());
				int sides = Integer.parseInt(sidesField.getText());
				sides = verifySideLimits(sides);
				int guesses = Integer.parseInt(guessesField.getText());
				pModel.createRandomPolygon(seed, sides, guesses);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null,
						"Seed must be an integer of fewer than 11 characters.",
						"Invalid Seed", JOptionPane.ERROR_MESSAGE, null);
			}
		}
	}

	private int verifySideLimits(int sides) {
		if (sides >= 21) {
			sides = 20;
			JOptionPane.showMessageDialog(null,
					"The maximum allowable number of vertices is 20.",
					"Too Many Vertices", JOptionPane.WARNING_MESSAGE,
					null);
		} else if (sides < 5) {
			sides = 5;
			JOptionPane.showMessageDialog(null,
					"The minimum allowable number of vertices is 5",
					"Too Few Vertices", JOptionPane.WARNING_MESSAGE,
					null);
		}
		return sides;
	}
}
