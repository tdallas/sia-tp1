package strategies;

import game.Board;
import org.junit.Test;
import strategies.nonInformed.DFS;
import strategies.utils.Path;

public class DFSTest {
    @Test
    public void resolvingTestLevelUsingDFS() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.TEST);
        DFS dfs = new DFS(board);
        Path finalPath = dfs.findSolution();
        System.out.println(finalPath);
    }

    @Test
    public void resolvingEasyLevelUsingDFS() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.EASY);
        DFS dfs = new DFS(board);
        Path finalPath = dfs.findSolution();
        System.out.println(finalPath);
    }

    @Test
    public void resolvingMediumLevelUsingDFS() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.MEDIUM);
        DFS dfs = new DFS(board);
        Path finalPath = dfs.findSolution();
        System.out.println(finalPath);
    }

    @Test
    public void resolvingHardLevelUsingDFS() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD);
        DFS dfs = new DFS(board);
        Path finalPath = dfs.findSolution();
        System.out.println(finalPath);
    }

    @Test
    public void resolvingHard2LevelUsingDFS() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD2);
        DFS dfs = new DFS(board);
        Path finalPath = dfs.findSolution();
        System.out.println(finalPath);
    }
}
