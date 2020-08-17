package strategies;

import game.Board;
import org.junit.Test;
import strategies.nonInformed.IDFS;
import strategies.utils.Path;

public class IDFSTest {
    @Test
    public void resolvingTestLevelUsingIDFS() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.TEST);
        IDFS idfs = new IDFS(board);
        Path finalPath = idfs.findSolution();
        System.out.println(finalPath);
    }

    @Test
    public void resolvingEasyLevelUsingIDFS() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.EASY);
        IDFS idfs = new IDFS(board);
        Path finalPath = idfs.findSolution();
        System.out.println(finalPath);
    }

    @Test
    public void resolvingMediumLevelUsingIDFS() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.MEDIUM);
        IDFS idfs = new IDFS(board);
        Path finalPath = idfs.findSolution();
        System.out.println(finalPath);
    }

    @Test
    public void resolvingHardLevelUsingIDFS() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD);
        IDFS idfs = new IDFS(board);
        Path finalPath = idfs.findSolution();
        System.out.println(finalPath);
    }

    @Test
    public void resolvingHard2LevelUsingIDFS() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD2);
        IDFS idfs = new IDFS(board);
        Path finalPath = idfs.findSolution();
        System.out.println(finalPath);
    }
}
