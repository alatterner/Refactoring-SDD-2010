import java.util.Random;

import polygonarea.PolygonAreaIF;
import polygonarea.PolygonIF;

public class PolygonArea implements PolygonAreaIF {

	final private PolygonIF oracle;

	public PolygonArea(final PolygonIF oracle) {
		this.oracle = oracle;
	}

	public double estimate(final int guesses) {
		boolean val;
		double yCord = 0;
		double xCord = 0;
		int pointsInside = 0;
		Random random = new Random();
		for (int i = 0; i < guesses; i++) {
			xCord = random.nextDouble() * 10;
			yCord = random.nextDouble() * 10;
			val = oracle.isInside(xCord, yCord);
			if (val) {
				pointsInside++;
			}
		}
		return 5;
	}

}
