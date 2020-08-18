package strategies.informed;

import game.Board;
import strategies.heuristics.Heuristic;

public class AStar extends PriorityQueueStrategy {

    public AStar(final Board board, final Heuristic heuristic) {
        super(board,  (firstNode, secondNode) ->
                (int) ((firstNode.getCost() + heuristic.evaluate(firstNode.getState())) -
                        (secondNode.getCost()) + heuristic.evaluate(secondNode.getState())));
    }

}
