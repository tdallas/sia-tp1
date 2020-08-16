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

    private void simulateMovesAndAddToQueue(final Node currentNode) {
        final List<Direction> directionsToMovePusher = board.getPusherPossibleDirectionsToMove(currentNode.getState());
        for (Direction direction : directionsToMovePusher) {
            Node newNode = generateNewNode(direction, currentNode);
            if(!visited.contains(newNode.getState())) {
                queue.add(newNode);
            }
        }
    }

    private Node generateNewNode(Direction direction, final Node currentNode) {
        Coordinate vectorCoordinate = Board.coordinateVectorMap.get(direction);
        Pusher newPusher = new Pusher(new Coordinate(
                vectorCoordinate.getX() + currentNode.getState().getPusher().getCoordinate().getX(),
                vectorCoordinate.getY() + currentNode.getState().getPusher().getCoordinate().getY()));
        Box box = board.getBoxInCoordinate(newPusher.getCoordinate(), currentNode.getState());
        // check whether is a box in that coordinate, if its, move it too and generate new node
        if (box != null) {
            Box newBox = new Box(box.getLabel(), new Coordinate(vectorCoordinate.getX() + box.getCoordinate().getX(),
                    vectorCoordinate.getY() + box.getCoordinate().getY()));
            State newState = new State(newPusher, currentNode.getState().getBoxes(), newBox);
            return new Node(currentNode, newState, new Step(currentNode.getState().getPusher().getCoordinate(), newPusher.getCoordinate()));
        }
        State newState = new State(newPusher, Set.copyOf(currentNode.getState().getBoxes()));
        return new Node(currentNode, newState, new Step(currentNode.getState().getPusher().getCoordinate(), newPusher.getCoordinate()));
    }

    @Override
    public Path findSolution() {
        setStartTime(System.currentTimeMillis());
        Node currentNode = new Node(null, board.getInitialState(), null, 0);
        queue.add(currentNode);
        while (!queue.isEmpty()) {
            currentNode = queue.poll();
            if (!visited.contains(currentNode.getState())) {
                if (!board.gameHasEnded(currentNode.getState())) {
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
        // DEADLOCK
        return null;
    }
}
