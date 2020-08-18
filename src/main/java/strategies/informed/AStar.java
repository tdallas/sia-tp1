package strategies.informed;

import game.Board;
import game.Direction;
import game.State;
import strategies.SearchStrategy;
import strategies.heuristics.Heuristic;
import strategies.heuristics.trivials.Euclidean;
import strategies.utils.Node;
import strategies.utils.Path;

import java.util.*;

public class AStar extends SearchStrategy {

    // initialize to default heuristic
    private Heuristic heuristic;
    public Comparator<Node> aStarComparator = (firstNode, secondNode) ->
            (int) ((firstNode.getCost() + heuristic.evaluate(firstNode.getState())) -
                    (secondNode.getCost()) + heuristic.evaluate(secondNode.getState()));
    private final Board board;
    private final Queue<Node> priorityQueue = new PriorityQueue<>(aStarComparator);
    private final Set<State> visited = new HashSet<>();

    public AStar(final Board board, final Heuristic heuristic) {
        this.board = board;
        this.heuristic = heuristic;
    }

    @Override
    public Path findSolution() {
        setStartTime(System.currentTimeMillis());
        Node currentNode = new Node(null, board.getInitialState(), null, 0);
        priorityQueue.add(currentNode);
        while (!priorityQueue.isEmpty()) {
            currentNode = priorityQueue.poll();
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
        return null;
    }

    private void simulateMovesAndAddToQueue(final Node currentNode) {
        final List<Direction> directionsToMovePusher = board.getPusherPossibleDirectionsToMove(currentNode.getState());
        for (Direction direction : directionsToMovePusher) {
            Node newNode = Node.generateNewNode(direction, currentNode);
            if(!visited.contains(newNode.getState())) {
                priorityQueue.add(newNode);
            }
        }
    }

}
