package strategies.nonInformed;

import game.Board;
import game.State;
import strategies.utils.Path;
import game.Pusher;
import lombok.Getter;
import game.Direction;
import strategies.SearchStrategy;
import strategies.utils.Node;
import strategies.utils.Step;

import java.util.*;

@Getter
public class DFS extends SearchStrategy {

    private final Board board;
    private Set<State> visited = new HashSet<>();

    public DFS(Board board) {
        this.board = board;
    }

    @Override
    public Path findSolution() {
        Node firstNode = new Node(null, board.getInitialState(), null, 0);
        if (board.gameHasEnded(firstNode.getState())) {
            return new Path(firstNode);
        }
        Stack<Node> stack = new Stack<>();
        stack.push(firstNode);
        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            if (!visited.contains(currentNode.getState())) {
                List<Direction> directionsToMove = board.getPusherPossibleDirectionsToMove(currentNode.getState());
                for (Direction direction : directionsToMove) {
                    final Node possibleEndNode = Node.generateNewNode(direction, currentNode, board);
                    if (board.gameHasEnded(possibleEndNode.getState())) {
                        System.out.println(possibleEndNode.getState().getBoxes());
                        return new Path(possibleEndNode);
                    }
                    stack.push(possibleEndNode);
                }
                visited.add(currentNode.getState());
            }
        }
        return null;
    }

    private Node findSolutionUsingDFS(final Node currentNode) {
        if (board.gameHasEnded(currentNode.getState())) {
            return currentNode;
        }
        if (visited.contains(currentNode.getState())) {
            return null;
        }
        visited.add(currentNode.getState());
        for (Direction direction : board.getPusherPossibleDirectionsToMove(currentNode.getState())) {
            final Node newNode = Node.generateNewNode(direction, currentNode, board);
            final Node possibleEndNode = findSolutionUsingDFS(newNode);
            if (possibleEndNode != null) {
                return possibleEndNode;
            }
        }
        return null;
    }
}
