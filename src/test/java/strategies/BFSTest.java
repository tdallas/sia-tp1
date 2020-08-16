package strategies;

import game.Board;
import org.junit.Test;
import strategies.nonInformed.BFS;
import strategies.utils.Path;

import static org.junit.Assert.assertNotNull;

public class BFSTest {
    @Test
    public void boardGenerationTest() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.MEDIUM);
        BFS bfs = new BFS(board);
        Path finalPath = bfs.findSolution();
        assertNotNull(finalPath);
    }
}
