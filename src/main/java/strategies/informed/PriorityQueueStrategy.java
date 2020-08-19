package strategies.informed;

import game.Board;
import game.Direction;
import game.State;
import strategies.SearchStrategy;
import strategies.heuristics.Heuristic;
import strategies.utils.Node;
import strategies.utils.Path;

import java.util.*;

public abstract class PriorityQueueStrategy extends SearchStrategy {
    // initialize to default heuristic
    private final Queue<Node> priorityQueue;
    private final Set<State> visited;

    public PriorityQueueStrategy(final Board board, final Comparator<Node> comparator) {
        super(board);
        this.priorityQueue = new PriorityQueue<>(comparator);
        this.visited = new HashSet<>();
    }

    @Override
    public Path findSolution() {
        setStartTime(System.currentTimeMillis());
        Node currentNode = new Node(null, getBoard().getInitialState(), null, 0);
        priorityQueue.add(currentNode);
        while (!priorityQueue.isEmpty()) {
            currentNode = priorityQueue.poll();
            if (!visited.contains(currentNode.getState())) {
                if (!getBoard().gameHasEnded(currentNode.getState())) {
                    simulateMovesAndAddToQueue(currentNode);
                } else {
                    setFinishTime(System.currentTimeMillis());
                    return new Path(currentNode);
                }
            }
            visited.add(currentNode.getState());
        }
        setFinishTime(System.currentTimeMillis());
        return null;
    }

    private void simulateMovesAndAddToQueue(final Node currentNode) {
        final List<Direction> directionsToMovePusher = getBoard().getPusherPossibleDirectionsToMove(currentNode.getState());
        expandedNodes++;
        for (Direction direction : directionsToMovePusher) {
            Node newNode = Node.generateNewNode(direction, currentNode);
            if(!visited.contains(newNode.getState()) && !getBoard().isDeadlock(newNode.getState())) {
                priorityQueue.add(newNode);
            }else{
                borderNodes++;
            }
        }
    }
}
