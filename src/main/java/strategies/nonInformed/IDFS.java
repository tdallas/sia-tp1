package strategies.nonInformed;

import game.Board;
import game.Direction;
import game.State;
import strategies.utils.Node;
import strategies.utils.Path;
import strategies.SearchStrategy;

import java.util.HashSet;
import java.util.Set;

public class IDFS extends SearchStrategy {

    private final Board board;
    private Set<State> visited;

    public IDFS(Board board) {
        this.board = board;
    }

    @Override
    public Path findSolution() {
        setStartTime(System.currentTimeMillis());
        Node currentNode = null;
        int depth = 1;
        while(currentNode == null || !board.gameHasEnded(currentNode.getState())){
            this.visited = new HashSet<>();
            currentNode = new Node(null, board.getInitialState(), null, 0);
            currentNode = findSolutionUsingIDFS(currentNode, depth++);
        }
        setFinishTime(System.currentTimeMillis());
        System.out.println("Final depth=" + depth);
        System.out.println("Time spent solving=" + getSolveTime());
        Path result = new Path(currentNode);
        System.out.println(result);
        return result;
    }

    private Node findSolutionUsingIDFS(final Node currentNode, int depth) {
        if (depth == 0){
            return null;
        }
        if (board.gameHasEnded(currentNode.getState())) {
            return currentNode;
        }
        if (visited.contains(currentNode.getState())) {
            return null;
        }
        visited.add(currentNode.getState());
        for (Direction direction : board.getPusherPossibleDirectionsToMove(currentNode.getState())) {
            final Node newNode = Node.generateNewNode(direction, currentNode, board);
            final Node possibleEndNode = findSolutionUsingIDFS(newNode, depth - 1);
            if (possibleEndNode != null) {
                return possibleEndNode;
            }
        }
        return null;
    }
}
