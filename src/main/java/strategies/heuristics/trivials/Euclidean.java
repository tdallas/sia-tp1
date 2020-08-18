package strategies.heuristics.trivials;

import game.Coordinate;
import game.State;
import strategies.heuristics.Heuristic;

import java.util.Set;

public class Euclidean extends Heuristic {

    public Euclidean(final Set<Coordinate> finishTiles) {
        super(finishTiles);
    }

    @Override
    public double evaluate(State currentState) {
        Set<Coordinate> boxCoordinatesSet = currentState.getBoxes();
        // Initialize sumValue to manhattan distance from player to closest box
        double sumValue = calculateMinDistance(currentState.getPusher(), boxCoordinatesSet);

        for (Coordinate boxCoordinate : boxCoordinatesSet) {
            sumValue += calculateMinDistance(boxCoordinate, getFinishTiles());
        }

        return sumValue;
    }

    @Override
    protected double calculateMinDistance(Coordinate current, Set<Coordinate> coordinateSet) {
        double minDistance = Double.MAX_VALUE;

        for (Coordinate coordinate : coordinateSet) {
            double currentManhattanDistance = euclideanDistance(current, coordinate);
            if (currentManhattanDistance < minDistance) {
                minDistance = currentManhattanDistance;
            }
        }

        return minDistance;
    }

    private double euclideanDistance(final Coordinate from, final Coordinate to) {
        return Math.sqrt(Math.pow(from.getX() - to.getX(), 2) + Math.pow(from.getY() - to.getY(), 2));
    }
}
