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
    private final Set<State> visited;

    public DFS(Board board) {
        this.board = board;
        this.visited = new HashSet<>();
    }

    @Override
    public Path findSolution() {
        setStartTime(System.currentTimeMillis());
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
                        currentNode = possibleEndNode;
                        setFinishTime(System.currentTimeMillis());
                        System.out.println("Time spent solving=" + getSolveTime());
                        Path result = new Path(currentNode);
                        System.out.println(result);
                        return result;
                    }
                    stack.push(possibleEndNode);
                }
                visited.add(currentNode.getState());
            }
        }
        setFinishTime(System.currentTimeMillis());
        return null;
    }
}
