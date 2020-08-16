package strategies;

import game.Board;
import game.Path;
import game.Step;
import org.junit.Test;
import strategies.nonInformed.BFS;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class BFSTest {
    @Test
    public void boardGenerationTest() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD);
        BFS bfs = new BFS(board);
        Path finalPath = bfs.findSolution();
        assertNotNull(finalPath);
    }
}
