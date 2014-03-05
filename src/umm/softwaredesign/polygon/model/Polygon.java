package umm.softwaredesign.polygon.model;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class Polygon {
    private final List<Point> points;
    private int numPoints;

    public Polygon() {
        points = new ArrayList<Point>();
        numPoints = 0;
    }

    public boolean checkIntersects(final Point first, final Point second,
            final Point third, final Point fourth) {
        return Line2D.linesIntersect(first.getX(), first.getY(), second.getX(),
                second.getY(), third.getX(), third.getY(), fourth.getX(),
                fourth.getY());
    }

    
    public void addPoint(final Point addPoint, final int position) {
        Point point1 = points.get(position);
        Point point2 = points.get((position + 1) % numPoints);
        if (isCounterClockWise(point1, point2, addPoint)
                && isCounterClockWise(points
                        .get(((position - 1) + numPoints) % numPoints),
                        addPoint, point1)
                && isCounterClockWise(addPoint, points.get((position + 2)
                        % numPoints), point2)) {
            points.add(position + 1, addPoint);
            numPoints++;
        }
    }

    public List<Point> getPointList() {
        return points;
    }

    public void addInitialPoint(final Point point) {
        points.add(point);
        numPoints++;
    }

    public boolean isCounterClockWise(final Point point1, final Point point2,
            final Point point3) {
        double checkIfCounterClockwise = ((point2.getX() - point1.getX()) * (point3.getY() - point2.getY()))
                - ((point2.getY() - point1.getY()) * (point3.getX() - point2.getX()));
        if (checkIfCounterClockwise < 0) {
            return true;
        }
        return false;
    }

    public boolean contains(final Point point) {
        Point[] bounds = {new Point(0.0, 0.0), new Point(0.0, 10.0), new Point(10.0, 0.0), new Point(10.0, 10.0)};      
        int intersect[] = {0,0,0,0};   
        for (int i = 0; i < bounds.length; i++) {
            for (int j = 0; j < numPoints; j++) {
                if (checkIntersects(point, bounds[i], points.get(j), points.get((j + 1) % numPoints))) {
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
            if (!isCounterClockWise(points.get((i-1+numPoints) % numPoints), points.get((i+1) % numPoints), points.get(i))) {
                return false;
            }
        }
        return true;
    }

    public double getArea() {
        return Math.abs(signedArea());
    }

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
