package strategies.nonInformed;

import game.Board;
import game.Direction;
import game.State;
import strategies.SearchStrategy;
import strategies.utils.Node;
import strategies.utils.Path;

import java.util.*;

public class BFS extends SearchStrategy {

    private final Queue<Node> queue;
    private final Set<State> visited;

    public BFS(final Board board) {
        super(board);
        this.queue = new ArrayDeque<>();
        this.visited = new HashSet<>();
    }

    private void simulateMovesAndAddToQueue(final Node currentNode) {
        final List<Direction> directionsToMovePusher = getBoard().getPusherPossibleDirectionsToMove(currentNode.getState());
        for (Direction direction : directionsToMovePusher) {
            Node newNode = Node.generateNewNode(direction, currentNode);
            if(!visited.contains(newNode.getState())) {
                queue.add(newNode);
            }
        }
    }

    @Override
    public Path findSolution() {
        setStartTime(System.currentTimeMillis());
        Node currentNode = new Node(null, getBoard().getInitialState(), null, 0);
        queue.add(currentNode);
        while (!queue.isEmpty()) {
            currentNode = queue.poll();
            if (!visited.contains(currentNode.getState())) {
                if (!getBoard().gameHasEnded(currentNode.getState())) {
                    simulateMovesAndAddToQueue(currentNode);
                } else {
                    setFinishTime(System.currentTimeMillis());
                    System.out.println("Time spent solving=" + getSolveTime());
                    Path result = new Path(currentNode);
                    System.out.println(result);
                    return result;
                }
            }
            visited.add(currentNode.getState());
        }
        setFinishTime(System.currentTimeMillis());
        return null;
    }
}
