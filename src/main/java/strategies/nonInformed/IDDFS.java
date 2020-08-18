package strategies.nonInformed;

import game.Board;
import game.Direction;
import game.State;
import strategies.utils.Node;
import strategies.utils.Path;
import strategies.SearchStrategy;

import java.util.HashSet;
import java.util.Set;

public class IDDFS extends SearchStrategy {

    private final int maxDepth;
    private Set<State> visited;

    public IDDFS(final Board board, int maxDepth) {
        super(board);
        this.maxDepth = maxDepth;
    }

    @Override
    public Path findSolution() {
        setStartTime(System.currentTimeMillis());
        Node currentNode = null;
        int depth = 1;
        while(depth <= maxDepth && (currentNode == null || !getBoard().gameHasEnded(currentNode.getState()))){
            this.visited = new HashSet<>();
            currentNode = new Node(null, getBoard().getInitialState(), null, 0);
            currentNode = findSolutionUsingIDDFS(currentNode, depth++);
        }
        setFinishTime(System.currentTimeMillis());
        if(currentNode != null) {
            System.out.println("Final depth=" + depth);
            System.out.println("Time spent solving=" + getSolveTime());
            Path result = new Path(currentNode);
            System.out.println(result);
            return result;
        }
        return null;
    }

    private Node findSolutionUsingIDDFS(final Node currentNode, int depth) {
        if (depth == 0 || visited.contains(currentNode.getState())){
            return null;
        }
        if (getBoard().gameHasEnded(currentNode.getState())) {
            return currentNode;
        }
        visited.add(currentNode.getState());
        for (Direction direction : getBoard().getPusherPossibleDirectionsToMove(currentNode.getState())) {
            final Node newNode = Node.generateNewNode(direction, currentNode);
            final Node possibleEndNode = findSolutionUsingIDDFS(newNode, depth - 1);
            if (possibleEndNode != null) {
                return possibleEndNode;
            }
        }
        return null;
    }
}
