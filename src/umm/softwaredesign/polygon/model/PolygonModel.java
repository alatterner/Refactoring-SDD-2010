package umm.softwaredesign.polygon.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import polygonarea.PolygonIF;


public class PolygonModel extends Observable implements PolygonIF {

    private Polygon polygon;
    private Random random;
    public int seedForDisplay;
    final private List<Point> guessedPoints;
    final private List<Point> correctPoints;
    final private List<Point> incorrectPoints;
    private int maxGuesses;
    private double guessedArea;
    private double score;
    private int randomNumberOfSides;

    public PolygonModel() {
        super();
        polygon = new Polygon();
        random = new Random();
        guessedPoints = new ArrayList<Point>();
        incorrectPoints = new ArrayList<Point>();
        correctPoints = new ArrayList<Point>();
        guessedArea = 0;
        score = 0;
    }

    public void createRandomPolygon() {
        clear();
        double math = Math.random();
        int seed =  (int) (math * 999999999);
        seedForDisplay = seed;
        int sides = (int) (Math.random()*20);
        int guesses = (int) (Math.random()*200);
        createRandomPolygon(seed, sides, guesses);
    }

    public void createRandomPolygon(final int seed, final int sides, final int guesses){
	random = new Random(seed);
	this.seedForDisplay = seed;
	clear();
	if(sides < 5){randomNumberOfSides = 5;}
	else{randomNumberOfSides = sides;}
	if(guesses < 100){maxGuesses = 100;}
	else if(guesses > 300){maxGuesses = 300;}
	else{maxGuesses = guesses;}
	polygon = new Polygon();
	generateInitialPoints();
        while (polygon.getNumPoints() < randomNumberOfSides) {
            polygon.addPoint(generateRandomPoint(), random.nextInt(polygon
                    .getNumPoints()));}
	setChanged();
	notifyObservers();
    }
    
    public void clear(){
        guessedPoints.clear();
        incorrectPoints.clear();
        correctPoints.clear();
        guessedArea=0;
        score=0;
    }

    private Point generateRandomPoint() {
        double coordX;
        double coordY;
        coordX = random.nextDouble() * 10;
        coordY = random.nextDouble() * 10;
        Point point = new Point(coordX, coordY);
        return point;
    }
    
    public Polygon getPoly() {
        return polygon;
    }
    
    public List<Point> getGuessedPoints() {
        return guessedPoints;
    }
    
    public List<Point> getIncorrect() {
        return incorrectPoints;
    }
    
    public List<Point> getCorrect() {
        return correctPoints;
    }

    private void generateInitialPoints() {
        for (int i = 0; i < 2; i++) {
            Point newP = generateRandomPoint();
            polygon.addInitialPoint(newP);
        }
    }

    public void scoreGuessedArea(final double area) {
        guessedArea = area;
        score = Math.max(0, 5 - Math.abs(guessedArea - polygon.getArea()));
        setChanged();
        notifyObservers();
    }
    

    @Override
    public boolean isInside(final double xCord, final double yCord) {
        Point guess = new Point(xCord, yCord);
        if (guessesRemaining() > 0) {
            guessedPoints.add(guess);
            if (polygon.contains(guess)) {
                correctPoints.add(guess);
            } else {
                incorrectPoints.add(guess);
            }
        } else { throw new TooManyGuessesException();
        }
        return polygon.contains(new Point(xCord, yCord));
    }

    public int getMaxGuesses() {
        return maxGuesses;
    }
    
    public double getGuessedArea() {
        return guessedArea;
    }
    
    private int guessesRemaining() {
        return maxGuesses - guessedPoints.size();
    }
    
    public double getScore() {
        return score;
    }

}
