package umm.softwaredesign.polygon.model;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Polygon {
	private final List<Point2D.Double> points;
	private int numPoints;

	public Polygon() {
		points = new ArrayList<Point2D.Double>();
		numPoints = 0;
	}

	public boolean checkIntersects(final Point2D.Double first, final Point2D.Double second,
			final Point2D.Double third, final Point2D.Double fourth) {
		return Line2D.linesIntersect(first.getX(), first.getY(), 
				second.getX(), second.getY(), 
				third.getX(), third.getY(), 
				fourth.getX(), fourth.getY());
	}

	public void addPoint(final Point2D.Double addPoint, final int position) {
		Point2D.Double point1 = points.get(position);
		Point2D.Double point2 = points.get((position + 1) % numPoints);
		if (isCounterClockWise(point1, point2, addPoint)
				&& isCounterClockWise(
						points.get(((position - 1) + numPoints) % numPoints), 
						addPoint, point1)
				&& isCounterClockWise(
						addPoint,points.get((position + 2) % numPoints), 
						point2)) {
			points.add(position + 1, addPoint);
			numPoints++;
		}
	}

	public List<Point2D.Double> getPointList() {
		return points;
	}

	public void addInitialPoint(final Point2D.Double point) {
		points.add(point);
		numPoints++;
	}

	
	//Determines if a given polygon is "clockwise," or "counterclockwise,"
	//A clockwise polygon is a positively oriented curve, such that when
	//traveling on it one always has the curve interior to the left. Exchanging
	//the terms left and right in the above description, you get a negatively
	//oriented curve.
	//Details provided herein: http://en.wikipedia.org/wiki/Curve_orientation
	public boolean isCounterClockWise(final Point2D.Double point1, final Point2D.Double point2,
			final Point2D.Double point3) {
		double firstXDifference = point2.getX() - point1.getX();
		double secondYDifference = point3.getY() - point2.getY();
		double firstYDifference = point2.getY() - point1.getY();
		double secondXDifference = point3.getX() - point2.getX();
		double checkIfCounterClockwise = (firstXDifference * secondYDifference)
									   - (firstYDifference * secondXDifference);
		
		if (checkIfCounterClockwise < 0) {
			return true;
		}
		return false;
	}

	//Determines if a point is inside the polygon by creating four lines from
	//that point to each corner and checking if they all intersect at least one
	//of the edges of the polygon.
	public boolean contains(final Point2D.Double point) {
		Point2D.Double[] bounds = { new Point2D.Double(0.0, 0.0), new Point2D.Double(0.0, 10.0),
				new Point2D.Double(10.0, 0.0), new Point2D.Double(10.0, 10.0) };
		int intersect[] = { 0, 0, 0, 0 };
		for (int i = 0; i < bounds.length; i++) {
			for (int j = 0; j < numPoints; j++) {
				if (checkIntersects(point, bounds[i], points.get(j),
						points.get((j + 1) % numPoints))) {
					intersect[i] = 1;
				}
			}
			if (intersect[i] == 0) {
				return false;
			}
		}
		return true;
	}

	public boolean isConvex() {
		for (int i = 0; i < numPoints; i++) {
			if (!isCounterClockWise(
					points.get((i - 1 + numPoints) % numPoints),
					points.get((i + 1) % numPoints), points.get(i))) {
				return false;
			}
		}
		return true;
	}

	public double getArea() {
		return Math.abs(signedArea());
	}

	//The signed area is exactly the area for non-intersecting polygons.
	//This might help: http://demonstrations.wolfram.com/SignedAreaOfAPolygon/
	public double signedArea() {
		double sum = 0.0;
		for (int i = 0; i < numPoints; i++) {
			sum = sum + (points.get(i).getX() * points.get((i + 1) % numPoints).getY())
					  - (points.get(i).getY() * points.get((i + 1) % numPoints).getX());
		}
		return 0.5 * sum;
	}

	public int getNumPoints() {
		return numPoints;
	}

}
