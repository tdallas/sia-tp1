package strategies;

import game.Step;

import java.util.List;

/**
 * I think this could be an interface also, it gets a little confusing for me if SearchStrategy is
 * an object or an object's behaviour tbh
 */
public abstract class SearchStrategy {
    /**
     * This function find a solution for a Sokoban initial board
     * @return
     */
    public abstract List<Step> findSolution();
}
