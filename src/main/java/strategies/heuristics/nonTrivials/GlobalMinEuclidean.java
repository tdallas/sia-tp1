package strategies.heuristics.nonTrivials;

import game.Coordinate;
import game.State;
import strategies.heuristics.Heuristic;

import java.util.Set;

public class GlobalMinEuclidean extends Heuristic {
    private double previous = Double.MAX_VALUE;

    public GlobalMinEuclidean(final Set<Coordinate> finishTiles) {
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
            currentDistance = euclideanDistance(current, coordinate);
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
}
