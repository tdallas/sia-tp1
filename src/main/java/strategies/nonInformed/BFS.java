package strategies.nonInformed;

import game.Board;
import game.Box;
import game.Path;
import game.Pusher;
import lombok.AllArgsConstructor;
import strategies.SearchStrategy;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class BFS extends SearchStrategy {

    public BFS(Board board) {
        this.board = board;
    }

    @AllArgsConstructor
    private class Node {
        private Pusher pusher;
        private List<Box> boxList;
    }

    private final Board board;
    private Queue<Node> queue = new ArrayDeque<>();
    private Queue<Node> adjacencyQueue = new ArrayDeque<>();

    @Override
    public Path findSolution() {
        return null;
    }

   /* @Override
    public Path findSolution(Board initial) {
        Board currentNode = initial;
        queue.add(initial);
        adjacencyQueue.add(initial);
        while (!queue.isEmpty()) {
            currentNode.getPusher()
        }
    }*/
}
