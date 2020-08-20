package strategies.informed;

import game.Board;
import game.Direction;
import game.State;
import strategies.SearchStrategy;
import strategies.heuristics.Heuristic;
import strategies.utils.Node;
import strategies.utils.Path;

import java.util.*;

public class IDAStar extends SearchStrategy {

    private final Heuristic heuristic;


    public IDAStar(final Board board, final Heuristic heuristic) {
        super(board);
        this.heuristic = heuristic;
    }

    @Override
    public Path findSolution(){
        setStartTime(System.currentTimeMillis());
        Node initialNode = new Node(null, getBoard().getInitialState(), null, 0);
        double cutoff = heuristic.evaluate(initialNode.getState());
        while (true) {

            Set<State> visited = new HashSet<>();
            PriorityQueue<Node> priorityQueue = new PriorityQueue<>((firstNode, secondNode) ->
                    (int) ((firstNode.getCost() + heuristic.evaluate(firstNode.getState())) -
                            (secondNode.getCost()) + heuristic.evaluate(secondNode.getState())));
            double nextCutoff = Double.MAX_VALUE;

            priorityQueue.add(initialNode);
            while (!priorityQueue.isEmpty()) {
                Node current = priorityQueue.poll();
                visited.add(current.getState());
                expandedNodes++;

                if (getBoard().gameHasEnded(current.getState())) {
                    setFinishTime(System.currentTimeMillis());
                    return new Path(current);
                }

                List<Direction> directions = getBoard().getPusherPossibleDirectionsToMove(current.getState());
                for (Direction direction : directions) {
                    Node newNode = Node.generateNewNode(direction, current);
                    if (!visited.contains(newNode.getState()) && !getBoard().isDeadlock(newNode.getState())) {
                        double fValue = heuristic.evaluate(newNode.getState());
                        if(fValue <= cutoff){
                            priorityQueue.add(newNode);
                        }
                        if (fValue < nextCutoff) {
                            nextCutoff = fValue;
                        }
                    }
                    else{
                        borderNodes++;
                    }
                }
            }
            cutoff = nextCutoff;
        }
    }
}
