package strategies;

import game.Board;
import org.junit.Test;
import strategies.nonInformed.IDDFS;
import strategies.utils.Path;

import static org.junit.Assert.assertNotNull;

public class IDDFSTest {
    @Test
    public void resolvingTestLevelUsingIDDFS() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.TEST);
        IDDFS IDDFS = new IDDFS(board, 1000);
        Path finalPath = IDDFS.findSolution();
        assertNotNull(finalPath);
    }

    @Test
    public void resolvingEasyLevelUsingIDDFS() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.EASY);
        IDDFS IDDFS = new IDDFS(board, 1000);
        Path finalPath = IDDFS.findSolution();
        assertNotNull(finalPath);
    }

    @Test
    public void resolvingMediumLevelUsingIDDFS() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.MEDIUM);
        IDDFS IDDFS = new IDDFS(board, 1000);
        Path finalPath = IDDFS.findSolution();
        assertNotNull(finalPath);
    }

    @Test
    public void resolvingHardLevelUsingIDDFS() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD);
        IDDFS IDDFS = new IDDFS(board, 1000);
        Path finalPath = IDDFS.findSolution();
        assertNotNull(finalPath);
    }

    @Test
    public void resolvingHard2LevelUsingIDDFS() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD2);
        IDDFS IDDFS = new IDDFS(board, 1000);
        Path finalPath = IDDFS.findSolution();
        assertNotNull(finalPath);
    }
}
