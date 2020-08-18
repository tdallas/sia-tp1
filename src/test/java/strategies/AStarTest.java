package strategies;

import game.Board;
import org.junit.Test;
import strategies.heuristics.trivials.Euclidean;
import strategies.heuristics.trivials.Manhattan;
import strategies.informed.AStar;
import strategies.utils.Path;

import java.util.HashSet;

import static org.junit.Assert.assertNotNull;

public class AStarTest {

    @Test
    public void mediumSolutionWithManhattan() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.MEDIUM);
        AStar aStar = new AStar(board, new Manhattan(new HashSet<>(board.getFinishCoordinates())));
        Path finalPath = aStar.findSolution();
        assertNotNull(finalPath);
    }

    @Test
    public void mediumSolutionWithEuclidean() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.MEDIUM);
        AStar aStar = new AStar(board, new Euclidean(new HashSet<>(board.getFinishCoordinates())));
        Path finalPath = aStar.findSolution();
        assertNotNull(finalPath);
    }

    @Test
    public void hardSolutionWithManhattan() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD);
        AStar aStar = new AStar(board, new Manhattan(new HashSet<>(board.getFinishCoordinates())));
        Path finalPath = aStar.findSolution();
        assertNotNull(finalPath);
    }

    @Test
    public void hardSolutionWithEuclidean() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD);
        AStar aStar = new AStar(board, new Euclidean(new HashSet<>(board.getFinishCoordinates())));
        Path finalPath = aStar.findSolution();
        assertNotNull(finalPath);
    }

    @Test
    public void hard2SolutionWithManhattan() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD2);
        AStar aStar = new AStar(board, new Manhattan(new HashSet<>(board.getFinishCoordinates())));
        Path finalPath = aStar.findSolution();
        assertNotNull(finalPath);
    }

    @Test
    public void hard2SolutionWithEuclidean() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD2);
        AStar aStar = new AStar(board, new Euclidean(new HashSet<>(board.getFinishCoordinates())));
        Path finalPath = aStar.findSolution();
        assertNotNull(finalPath);
    }

    @Test
    public void easySolutionWithEuclidean() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.EASY);
        AStar aStar = new AStar(board, new Euclidean(new HashSet<>(board.getFinishCoordinates())));
        Path finalPath = aStar.findSolution();
        assertNotNull(finalPath);
    }
}
