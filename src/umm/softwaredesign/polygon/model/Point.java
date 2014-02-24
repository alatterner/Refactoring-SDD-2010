package umm.softwaredesign.polygon.model;


public class Point {
    private final double xCoord;
    private final double yCoord;
    
    public Point(final double xInput, final double yInput) {
        xCoord = xInput;
        yCoord = yInput;
    }
    
    public double getX() {
        return this.xCoord;
    }
    
    public double getY() {
        return this.yCoord;
    }
}
