package strategies.informed;

import game.Board;
import strategies.heuristics.Heuristic;

public class Greedy extends PriorityQueueStrategy {

    public Greedy(final Board board, final Heuristic heuristic) {
        super(board,  (firstNode, secondNode) ->
                (int) (heuristic.evaluate(firstNode.getState()) - heuristic.evaluate(secondNode.getState())));
    }
}
