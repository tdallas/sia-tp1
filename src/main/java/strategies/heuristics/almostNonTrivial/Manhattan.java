package strategies.heuristics.almostNonTrivial;

import game.Coordinate;
import game.State;
import strategies.heuristics.Heuristic;

import java.util.Set;

public class Manhattan extends Heuristic {

    public Manhattan(final Set<Coordinate> finishTiles) {
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
        double minDistance = Double.MAX_VALUE, currentDistance;

        for (Coordinate coordinate : coordinateSet) {
            currentDistance = manhattanDistance(current, coordinate);
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
            }
        }

        return minDistance;
    }

    private double manhattanDistance(final Coordinate from, final Coordinate to) {
        return Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY());
    }
}
