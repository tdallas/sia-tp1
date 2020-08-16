package strategies;

import game.Board;
import org.junit.Test;
import strategies.nonInformed.BFS;
import strategies.utils.Path;

import static org.junit.Assert.*;

public class BFSTest {

    @Test
    public void testSolutionShouldBe3() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.TEST);
        BFS bfs = new BFS(board);
        Path finalPath = bfs.findSolution();
        assertNotNull(finalPath);
        assertEquals(3, finalPath.getLength());
    }

    @Test
    public void easySolutionShouldBe2() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.EASY);
        BFS bfs = new BFS(board);
        Path finalPath = bfs.findSolution();
        assertNotNull(finalPath);
        assertEquals(2, finalPath.getLength());
    }

    @Test
    public void mediumSolutionShouldBe7() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.MEDIUM);
        BFS bfs = new BFS(board);
        Path finalPath = bfs.findSolution();
        assertNotNull(finalPath);
        assertEquals(7, finalPath.getLength());
    }

    @Test
    public void hard2SolutionShouldBe23() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD2);
        BFS bfs = new BFS(board);
        Path finalPath = bfs.findSolution();
        assertNotNull(finalPath);
        assertEquals(23, finalPath.getLength());
    }

    @Test
    public void hardSolutionShouldBe86() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD);
        BFS bfs = new BFS(board);
        Path finalPath = bfs.findSolution();
        assertNotNull(finalPath);
        assertEquals(86, finalPath.getLength());
    }
}
