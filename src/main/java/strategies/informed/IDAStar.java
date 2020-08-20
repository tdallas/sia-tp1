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
    private Stack<Node> stack;
    private Set<State> visited;
    private final Heuristic heuristic;
    private List<Double> limits = new ArrayList<>();
    private Double currentMinValue = Double.MAX_VALUE;

    public IDAStar(final Board board, final Heuristic heuristic) {
        super(board);
        this.stack = new Stack<>();
        this.visited = new HashSet<>();
        this.heuristic = heuristic;
    }

    @Override
    public Path findSolution() {
        setStartTime(System.currentTimeMillis());
        Node initialNode = new Node(null, getBoard().getInitialState(), null, 0);
        double initialLimit = heuristic.evaluate(initialNode.getState());
        limits.add(initialLimit);
        while (true) {
            stack = new Stack<>();
            stack.push(initialNode);
            Node endNode = findRecursiveSolution(stack, 0, limits.get(limits.size() - 1));
            visited = new HashSet<>();
            limits.add(currentMinValue);
            currentMinValue = Double.MAX_VALUE;
            if (endNode != null && getBoard().gameHasEnded(endNode.getState())) {
                return new Path(endNode);
            }
        }
    }

    private Node findRecursiveSolution(Stack<Node> stack, double nodeCost, double bound) {
        if (stack.isEmpty()) {
            return null;
        }
        Node currentNode = stack.pop();
        visited.add(currentNode.getState());
        double currentFValue = currentNode.getCost() + heuristic.evaluate(currentNode.getState());

        if (currentFValue < currentMinValue) {
            if (!limits.contains(currentFValue)) {
                currentMinValue = currentFValue;
            }
        }

        if (currentFValue > bound) {
            return null;
        }
        if (getBoard().gameHasEnded(currentNode.getState())) {
            return currentNode;
        }
        final List<Direction> directionsToMovePusher = getBoard().getPusherPossibleDirectionsToMove(currentNode.getState());

        PriorityQueue<Node> currentPQ = new PriorityQueue<>((firstNode, secondNode) ->
                (int) ((firstNode.getCost() + heuristic.evaluate(firstNode.getState())) -
                        (secondNode.getCost()) + heuristic.evaluate(secondNode.getState())));
        for (Direction direction : directionsToMovePusher) {
            Node newNode = Node.generateNewNode(direction, currentNode);
            currentPQ.add(newNode);
        }

        while (!currentPQ.isEmpty()) {
            Node leastWeightNode = currentPQ.poll();
            if (leastWeightNode != null && !visited.contains(leastWeightNode.getState())) {
                if (!getBoard().isDeadlock(leastWeightNode.getState())) {
                    stack.push(leastWeightNode);
                    double possibleNewBound = heuristic.evaluate(leastWeightNode.getState()) + leastWeightNode.getCost();
                    if (!limits.contains(possibleNewBound) && possibleNewBound < currentMinValue) {
                        currentMinValue = possibleNewBound;
                    }
                    Node possibleEndNode = findRecursiveSolution(stack, nodeCost + 1, bound);
                    if (possibleEndNode != null && getBoard().gameHasEnded(possibleEndNode.getState())) {
                        return possibleEndNode;
                    }
                }
            }
        }

        return null;
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
