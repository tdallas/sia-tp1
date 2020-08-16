package strategies.nonInformed;

import game.*;
import strategies.utils.Path;
import strategies.SearchStrategy;
import strategies.utils.Node;
import strategies.utils.Step;

import java.util.*;

public class BFS extends SearchStrategy {

    public BFS(Board board) {
        this.board = board;
    }

    private final Board board;
    private Queue<Node> queue = new ArrayDeque<>();
    private Set<State> visited = new HashSet<>();

    private void setStateToBoard(final Node node) {
        board.setState(node.getState());
    }

    private void simulateMovesAndAddToQueue(final Node currentNode) {
        final List<Direction> directionsToMovePusher = board.getPusherPossibleDirectionsToMove();
        for (Direction direction : directionsToMovePusher) {
            Node newNode = new Node(currentNode, new State(currentNode.getState()));
            Coordinate previousCoordinate = currentNode.getState().getPusher().getCoordinate();
            setStateToBoard(newNode);
            Coordinate newCoordinate = board.moveTo(direction);
            newNode.setStep(new Step(previousCoordinate, newCoordinate));
            if(!visited.contains(newNode.getState())) {
                queue.add(newNode);
            }
        }
        setStateToBoard(currentNode);
    }

    @Override
    public Path findSolution() {
        Node currentNode = new Node(null, board.getState(), null, 0);
        queue.add(currentNode);
        while (!queue.isEmpty()) {
            currentNode = queue.poll();
            System.out.println(currentNode);
            setStateToBoard(currentNode);
            if (!visited.contains(currentNode.getState())) {
                if (!board.gameHasEnded()) {
                    simulateMovesAndAddToQueue(currentNode);
                } else {
                    Path result = new Path(currentNode);
                    System.out.println(result);
                    return result;
                }
            } else {
                System.out.println("Ya visitado");
            }
            visited.add(currentNode.getState());
        }
        // DEADLOCK
        return null;
    }
}
