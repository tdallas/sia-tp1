package strategies.heuristics;

import game.Coordinate;
import game.State;
import lombok.Getter;

import java.util.Set;

@Getter
public abstract class Heuristic {

    private final Set<Coordinate> finishTiles;

    public Heuristic(final Set<Coordinate> finishTiles) {
        this.finishTiles = finishTiles;
    }

    /**
     * Evaluates heuristic for current state and returns heuristic function's value
     * @param currentState
     * @return
     */
    public abstract double evaluate(final State currentState);

    /**
     *   Returns the min distance from current Coordinate to each Coordinate in set
     **/
    protected abstract double calculateMinDistance(final Coordinate current, final Set<Coordinate> coordinateSet);

}
