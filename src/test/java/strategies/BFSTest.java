package strategies;

import game.Board;
import org.junit.Test;
import strategies.nonInformed.BFS;
import strategies.utils.Path;

public class BFSTest {
    @Test
    public void boardGenerationTest() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD);
        BFS bfs = new BFS(board);
        Path finalPath = bfs.findSolution();
        System.out.printf("Final path %s \n", finalPath);
    }
}
