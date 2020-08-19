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

    // initialize to default heuristic
    private final Stack<Node> stack;
    private final Set<State> visited;
    private final Heuristic heuristic;

    public IDAStar(final Board board, final Heuristic heuristic) {
        super(board);
        this.stack = new Stack<>();
        this.visited = new HashSet<>();
        this.heuristic = heuristic;
    }

    @Override
    public Path findSolution() {
        setStartTime(System.currentTimeMillis());
        Node currentNode = new Node(null, getBoard().getInitialState(), null, 0);
        stack.push(currentNode);
        Node endNode = findRecursiveSolution(stack, 0, 8, null);
        setFinishTime(System.currentTimeMillis());
        return new Path(endNode);
    }

    private Node findRecursiveSolution(Stack<Node> stack, double nodeCost, double bound, Node endNode) {
        if (stack.isEmpty()) {
            return endNode;
        }
        Node currentNode = stack.pop();
        visited.add(currentNode.getState());
        double currentFValue = nodeCost + heuristic.evaluate(currentNode.getState());
        if (currentFValue > bound) {
            return endNode;
        }
        if (getBoard().gameHasEnded(currentNode.getState())) {
            return currentNode;
        }
        Node currentEndNode = endNode;
        final List<Direction> directionsToMovePusher = getBoard().getPusherPossibleDirectionsToMove(currentNode.getState());
        for (Direction direction : directionsToMovePusher) {
            Node newNode = Node.generateNewNode(direction, currentNode);
            if (!visited.contains(newNode.getState())) {
                if (!getBoard().isDeadlock(newNode.getState())) {
                    stack.push(newNode);
                    double threshold = currentEndNode == null ? bound : Math.max(heuristic.evaluate(currentEndNode.getState()), bound);
                    Node possibleEndNode = findRecursiveSolution(stack, nodeCost + 1,
                            threshold, currentEndNode);
                    if (possibleEndNode != null && getBoard().gameHasEnded(possibleEndNode.getState()) && (endNode == null || threshold > heuristic.evaluate(possibleEndNode.getState()))) {
                    //    System.out.println("cambio");
                        System.out.print(heuristic.evaluate((possibleEndNode.getState())) + " " );
                        System.out.println(new Path(possibleEndNode));
                        currentEndNode = possibleEndNode;
                    }
                }
            }
        }
        return currentEndNode;
    }

    private void simulateMovesAndAddToQueue(final Node currentNode) {
        final List<Direction> directionsToMovePusher = getBoard().getPusherPossibleDirectionsToMove(currentNode.getState());
        for (Direction direction : directionsToMovePusher) {
            Node newNode = Node.generateNewNode(direction, currentNode);
            if (!visited.contains(newNode.getState()) && !getBoard().isDeadlock(newNode.getState())) {
            }
        }
    }
}
