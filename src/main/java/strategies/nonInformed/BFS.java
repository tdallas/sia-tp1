package strategies.nonInformed;

import game.Board;
import game.Box;
import game.Step;
import game.Pusher;
import strategies.Direction;
import strategies.SearchStrategy;

import java.util.*;

public class BFS extends SearchStrategy {

    public BFS(Board board) {
        this.board = board;
    }

    private static List<Box> cloneBoxList(List<Box> orig) {
        List<Box> clone = new ArrayList<>(Collections.nCopies(orig.size(), new Box()));
        Collections.copy(clone, orig);
        return clone;
    }

    private static List<Step> cloneStepList(List<Step> orig) {
        List<Step> clone = new ArrayList<>(Collections.nCopies(orig.size(), new Step()));
        Collections.copy(clone, orig);
        return clone;
    }

    private static class Node {
        private final Pusher pusher;
        private List<Box> boxList;

        public Node(final Pusher pusher, final List<Box> srcList) {
            this.pusher = pusher;
            this.pusher.setPath(cloneStepList(pusher.getPath()));
            this.boxList = cloneBoxList(srcList);
        }

        public List<Box> getBoxList() {
            return boxList;
        }

        public Pusher getPusher() {
            return pusher;
        }

        public boolean equals(final Node node) {
            boolean pusherEquals = this.getPusher().getCurrentCoordinate().equals(node.getPusher().getCurrentCoordinate());
            boolean boxesEquals = boxList.size() == node.getBoxList().size();
            for (int i = 0; i < boxList.size() && boxesEquals; i++) {
                boxesEquals = boxList.get(i).getCoordinate().equals(node.getBoxList().get(i).getCoordinate());
            }
            return pusherEquals && boxesEquals;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            boxList.forEach(box -> stringBuffer.append(box.toString()));
            return pusher.getCurrentCoordinate().toString() + stringBuffer.toString();
        }

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
