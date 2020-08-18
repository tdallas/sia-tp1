package strategies.heuristics.nonTrivials;

import game.Coordinate;
import game.State;
import strategies.heuristics.Heuristic;

import java.util.Set;

public class GlobalMinManhattan extends Heuristic {

    private double previous = Double.MAX_VALUE;

    public GlobalMinManhattan(final Set<Coordinate> finishTiles) {
        super(finishTiles);
    }

    @Override
    public double evaluate(State currentState) {
        Set<Coordinate> boxCoordinatesSet = currentState.getBoxes();

        double sumValue = calculateMinDistance(currentState.getPusher(), boxCoordinatesSet);

        for (Coordinate boxCoordinate : boxCoordinatesSet) {
            sumValue += calculateMinDistance(boxCoordinate, getFinishTiles());
        }

        return sumValue;
    }

    @Override
    protected double calculateMinDistance(Coordinate current, Set<Coordinate> coordinateSet) {
        for (Coordinate coordinate : coordinateSet) {
            double currentManhattanDistance = manhattanDistance(current, coordinate);
            if (currentManhattanDistance < previous) {
                previous = currentManhattanDistance;
                return currentManhattanDistance;
            }
        }

        return previous - (previous * 0.05);
    }

    private double euclideanDistance(final Coordinate from, final Coordinate to) {
        return Math.sqrt(Math.pow(from.getX() - to.getX(), 2) + Math.pow(from.getY() - to.getY(), 2));
    }

    private double manhattanDistance(final Coordinate from, final Coordinate to) {
        return Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY());
    }
}
