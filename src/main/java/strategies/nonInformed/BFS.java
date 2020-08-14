package strategies.nonInformed;

import game.Board;
import game.Pusher;
import game.Step;
import strategies.Direction;
import strategies.SearchStrategy;
import strategies.utils.Node;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BFS extends SearchStrategy {

    public BFS(Board board) {
        this.board = board;
    }

    private final Board board;
    private Queue<Node> queue = new ArrayDeque<>();
    private List<String> visited = new ArrayList<>();

    private void setDataToBoard(final Node node) {
        board.setBoxList(new ArrayList<>(node.getBoxList()));
        board.setPusher(new Pusher(node.getPusher()));
    }

    private void simulateMovesAndAddToQueue(final Node currentNode) {
        final List<Direction> directionsToMovePusher = board.getPusherPossibleDirectionsToMove();
        for (Direction direction : directionsToMovePusher) {
            Node backupNode = new Node(currentNode.getPusher(), currentNode.getBoxList());
            setDataToBoard(backupNode);
            board.moveTo(direction);
            printPath(board.getPusher().getPath());
            Node newNode = new Node(board.getPusher(), board.getBoxList());
            queue.add(newNode);
        }
        setDataToBoard(currentNode);
    }

    private void printPath(final List<Step> steps) {
        System.out.println("Steps size "+ steps.size());
        //steps.forEach(step -> System.out.println("(" + step.getFrom().toString() + " - " + step.getTo().toString() + ")"));
    }

    @Override
    public List<Step> findSolution() {
        Node currentNode = new Node(board.getPusher(), board.getBoxList());
        queue.add(currentNode);
        while (!queue.isEmpty()) {
            currentNode = queue.poll();
            System.out.println(currentNode);
            if (!visited.contains(currentNode.toString())) {
                if (!board.gameHasEnded(currentNode.getBoxList())) {
                    simulateMovesAndAddToQueue(currentNode);
                } else {
                    setDataToBoard(currentNode);
                    return currentNode.getPusher().getPath();
                }
            } else {
                System.out.println("Ya visitado");
            }
            visited.add(currentNode.toString());
        }
        // DEADLOCK
        return null;
    }
}
