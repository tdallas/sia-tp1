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
            currentDistance = euclideanDistance(current, coordinate);
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
            }
        }

        return minDistance;
    }

    private double euclideanDistance(final Coordinate from, final Coordinate to) {
        return Math.hypot(from.getX() - to.getX(), from.getY() - to.getY());
    }
}
