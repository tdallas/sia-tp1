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

        double sumValue = calculateMinDistance(currentState.getPusher(), currentState.getBoxes());

        for (Coordinate boxCoordinate : currentState.getBoxes()) {
            sumValue += calculateMinDistance(boxCoordinate, getFinishTiles());
        }

        return sumValue;
    }

    @Override
    protected double calculateMinDistance(Coordinate current, Set<Coordinate> coordinateSet) {
        double currentDistance;
        for (Coordinate coordinate : coordinateSet) {
            currentDistance = manhattanDistance(current, coordinate);
            if (currentDistance < previous) {
                previous = currentDistance;
                return currentDistance;
            }
        }

        return previous - (previous * 0.05);
    }

    private double euclideanDistance(final Coordinate from, final Coordinate to) {
        return Math.hypot(from.getX() - to.getX(), from.getY() - to.getY());
    }

    private double manhattanDistance(final Coordinate from, final Coordinate to) {
        return Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY());
    }
}
