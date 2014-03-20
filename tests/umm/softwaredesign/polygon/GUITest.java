package umm.softwaredesign.polygon;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.uispec4j.Panel;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;
import org.uispec4j.interception.MainClassAdapter;

import umm.softwaredesign.polygon.main.Main;

public class GUITest extends UISpecTestCase {
	private Window window;
	private Panel mainPanel;
	private Panel scorePanel;
	private Panel genPoly;
	private final static String ACTUAL_AREA = "Actual Area";

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		setAdapter(new MainClassAdapter(Main.class, new String[0]));
		window = getMainWindow();
		mainPanel = window.getPanel("Main Panel");
		scorePanel = mainPanel.getPanel("Side Panel").getPanel("Score Panel");
		genPoly = mainPanel.getPanel("Side Panel").getPanel(
				"GeneratePolygon Panel");
	}

	@Test
	public void testVisibilityOfEverythingMuahahahah() {
		assertTrue("Main panel: NOT visible!", mainPanel.isVisible());
		assertTrue("Console panel: NOT visible!",
				mainPanel.getPanel("Console Panel").isVisible());
		assertTrue("Polygon panel: NOT visible!", genPoly.isVisible());
		assertTrue("Score panel: NOT visible!", scorePanel.isVisible());
		assertTrue("Points table: NOT visible", window.getPanel("Points Panel")
				.getPanel("Points Table").isVisible());
	}

	@Test
	public void testSetParameters() {
		final String seed = "Seed Field";
		final String guess = "Guesses Field";
		final String vert = "Vertices Field";
		final String poly = "Seeded Polygon";
		final String incorrect = "Incorrect Area";

		genPoly.getTextBox(seed).setText("55116");
		genPoly.getTextBox(guess).setText("300");
		genPoly.getTextBox(vert).setText("15");
		genPoly.getButton(poly).click();
		assertEquals(incorrect, "53.0701", getActArea().substring(22, 29));

		genPoly.getTextBox(seed).setText("55407");
		genPoly.getTextBox(guess).setText("60");// This is too small a value -
												// should be set up to 100
		genPoly.getTextBox(vert).setText("6");
		genPoly.getButton(poly).click();
		assertEquals(incorrect, "33.5814", getActArea().substring(22, 29));
		assertEquals("Invalid number of guesses",
				genPoly.getTextBox("Guesses Field").getText(), "100");

		genPoly.getTextBox(seed).setText("4517777");
		genPoly.getTextBox(guess).setText("9001");// This is too large a value -
													// should be set back down
													// to 300
		genPoly.getTextBox(vert).setText("35"); // This is too large a value -
												// should be set back down to 20
		genPoly.getButton(poly).click();
		assertEquals(incorrect, "49.2843", getActArea().substring(22, 29));
		assertEquals("Invalid number of vertices",
				genPoly.getTextBox("Vertices field").getText(), "20");
		assertEquals("Invalid number of guesses",
				genPoly.getTextBox("Guesses Field").getText(), "300");

		genPoly.getTextBox(seed).setText("55116");
		genPoly.getTextBox(guess).setText("300");
		genPoly.getTextBox(vert).setText("2");// This is too small a value, both
												// mathematically and for our
												// program - should be set to 5.
		genPoly.getButton(poly).click();
		assertEquals(incorrect, "27.6805", getActArea().substring(22, 29));
		assertEquals("Invalid number of vertices",
				genPoly.getTextBox("Vertices field").getText(), "5");

		genPoly.getTextBox(seed).setText("9116518675309"); // This is an invalid
															// seed. This should
															// be checked for
															// and corrected, or
															// an exception will
															// be thrown.
		genPoly.getButton(poly).click();

	}

	private String getActArea() {
		return scorePanel.getTextBox(ACTUAL_AREA).getText();
	}

	@Test
	public void testRunAndScore() {
		String initScore = scorePanel.getTextBox("Score Box").getText();
		assertEquals("Score incorrect", "0.0000", initScore.substring(37, 43));
		mainPanel
				.getPanel("Top Panel")
				.getTextBox("File Field")
				.setText(
						"/home/bond0107/workspace/PolygonTestHarness/bin/PolygonArea.class");
		mainPanel.getPanel("Top Panel").getButton("Run").click();
		genPoly.getButton("Clear Console").click();
		assertEquals("Console not reset", "",
				mainPanel.getPanel("Console Panel")
						.getTextBox("Console Output").getText());
	}

	@Test(expected = IOException.class)
	public void testBlankFileFails() {
		mainPanel
				.getPanel("Top Panel")
				.getTextBox("File Field")
				.setText(
						"/home/bond0107/workspace/PolygonTestHarness/bin/PolygonArea.class");
		mainPanel.getPanel("Top Panel").getButton("Run").click();
	}

	@Test
	public void testRandomPolySortOf() {
		String before = getActArea();
		genPoly.getButton("Random Polygon").click();
		String after = getActArea();
		assertFalse("Equal", before.equals(after));
	}

	@Test
	public void testToggleColorSchemeKinda() {
		genPoly.getRadioButton("colorButton").click();
		genPoly.getRadioButton("colorButton").click();
	}

}
