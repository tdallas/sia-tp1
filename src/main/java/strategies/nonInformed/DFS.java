package strategies.nonInformed;

import game.Board;
import game.Pusher;
import game.Step;
import lombok.Getter;
import strategies.Direction;
import strategies.SearchStrategy;
import strategies.utils.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class DFS extends SearchStrategy {

    private final Board board;

    public DFS(Board board) {
        this.board = board;
    }

    private Map<String, Boolean> visitedMap = new HashMap<>();
    private Direction[] directionsDone = new Direction[1000];
    private int directionsCount = 0;

    private void setDataToBoard(final Node node) {
        board.setBoxList(new ArrayList<>(node.getBoxList()));
        board.setPusher(new Pusher(node.getPusher()));
    }

    @Override
    public List<Step> findSolution() {
        Node firstNode = new Node(board.getPusher(), board.getBoxList());
        findSolutionUsingDFS(firstNode);
        if (board.gameHasEnded(board.getBoxList())) {
            return board.getPusher().getPath().getSteps();
        }
        return null;
    }

    private void findSolutionUsingDFS(final Node currentNode) {
        Boolean visited = visitedMap.get(currentNode.toString());
        boolean gameHasEnded = board.gameHasEnded(board.getBoxList());
        if (gameHasEnded || (visited != null && visited)) {
            if (!gameHasEnded) {
                setDataToBoard(currentNode);
            }
            return;
        }
        List<Direction> posibleDirections = board.getPusherPossibleDirectionsToMove();
        for (Direction direction : posibleDirections) {
            Node backupNode = new Node(currentNode.getPusher(), currentNode.getBoxList());
            setDataToBoard(backupNode);
            board.moveTo(direction);
            if (board.gameHasEnded(board.getBoxList())) {
                return;
            }
            // Optimization
            Node newNode = new Node(board.getPusher(), board.getBoxList());
            if (!(visitedMap.get(newNode.toString()) != null && visitedMap.get(newNode.toString()))) {
                visitedMap.put(currentNode.toString(), true);
                directionsDone[directionsCount++] = direction;
                findSolutionUsingDFS(newNode);
            }
        }
    }
}
