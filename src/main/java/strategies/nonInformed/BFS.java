package strategies.nonInformed;

import game.Board;
import strategies.utils.Path;
import game.Pusher;
import game.Direction;
import strategies.SearchStrategy;
import strategies.utils.Node;

import java.util.*;

public class BFS extends SearchStrategy {

    public BFS(Board board) {
        this.board = board;
    }

    private final Board board;
//    private Queue<Node> queue = new ArrayDeque<>();
//    private Set<Node> visited = new HashSet<>();
//
//    private void setStateToBoard(final Node node) {
//
//        board.setBoxList(new ArrayList<>(node.getBoxList()));
//        board.setPusher(new Pusher(node.getPusher()));
//    }
//
//    private void simulateMovesAndAddToQueue(final Node currentNode) {
//        setDataToBoard(currentNode);
//        final List<Direction> directionsToMovePusher = board.getPusherPossibleDirectionsToMove();
//        for (Direction direction : directionsToMovePusher) {
//            Node backupNode = new Node(currentNode.getPusher(), currentNode.getBoxList());
//            setDataToBoard(backupNode);
//            board.moveTo(direction);
//            printPath(board.getPusher().getPath());
//            Node newNode = new Node(board.getPusher(), board.getBoxList());
//            if(!visited.contains(newNode)) {
//                queue.add(newNode);
//            }
//        }
//        setDataToBoard(currentNode);
//    }
//
//    private void printPath(final Path path) {
//        System.out.println("Steps size "+ path.getSteps().size());
//        //path.getSteps().forEach(step -> System.out.println("(" + step.getFrom().toString() + " - " + step.getTo().toString() + ")"));
//    }

    @Override
    public Path findSolution() {
//        Node currentNode = new Node(board.getPusher(), board.getBoxList());
//        queue.add(currentNode);
//        while (!queue.isEmpty()) {
//            currentNode = queue.poll();
//            System.out.println(currentNode);
//            if (!visited.contains(currentNode)) {
//                if (!board.gameHasEnded(currentNode.getBoxList())) {
//                    simulateMovesAndAddToQueue(currentNode);
//                } else {
//                    setDataToBoard(currentNode);
//                    return currentNode.getPusher().getPath();
//                }
//            } else {
//                System.out.println("Ya visitado");
//            }
//            visited.add(currentNode);
//        }
//        // DEADLOCK
        return null;
    }
}
