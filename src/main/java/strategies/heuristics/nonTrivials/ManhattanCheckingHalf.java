package strategies.heuristics.nonTrivials;

import game.Coordinate;
import game.State;
import strategies.heuristics.Heuristic;

import java.util.Set;

public class ManhattanCheckingHalf extends Heuristic {

    public ManhattanCheckingHalf(final Set<Coordinate> finishTiles) {
        super(finishTiles);
    }

    @Override
    public double evaluate(State currentState) {

        double sumValue = calculateMinDistance(currentState.getPusher(), currentState.getBoxes());

        int limit = currentState.getBoxes().size() / 2;
        int currentIteration = 0;

        for (Coordinate boxCoordinate : currentState.getBoxes()) {
            currentIteration++;
            sumValue += calculateMinDistance(boxCoordinate, getFinishTiles());
            if (currentIteration >= limit) {
                return sumValue;
            }
        }

        return sumValue;
    }

    @Override
    protected double calculateMinDistance(Coordinate current, Set<Coordinate> coordinateSet) {
        double minDistance = Double.MAX_VALUE, currentDistance;

        int limit = coordinateSet.size() / 2;
        int currentIteration = 0;

        for (Coordinate coordinate : coordinateSet) {
            currentIteration++;
            currentDistance = manhattanDistance(current, coordinate);
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
            }
            if (currentIteration >= limit) {
                return minDistance;
            }

        }

        return minDistance;
    }

    private double manhattanDistance(final Coordinate from, final Coordinate to) {
        return Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY());
    }
}
