package strategies.nonInformed;

import game.Board;
import game.Path;
import strategies.SearchStrategy;

import java.util.Queue;

public class BFS extends SearchStrategy {

    private Queue<Board> queue;
    private Queue<Board> adjacencyQueue;

    @Override
    public Path findSolution(Board initial) {
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
