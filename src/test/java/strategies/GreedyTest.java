package strategies;

import game.Board;
import game.BoardFactory;
import org.junit.Test;
import strategies.heuristics.almostNonTrivial.Euclidean;
import strategies.heuristics.almostNonTrivial.Manhattan;
import strategies.informed.Greedy;
import strategies.utils.Path;

import java.util.HashSet;

import static org.junit.Assert.assertNotNull;

public class GreedyTest {

    @Test
    public void mediumSolutionWithManhattan() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.MEDIUM);
        Greedy greedy = new Greedy(board, new Manhattan(new HashSet<>(board.getFinishCoordinates())));
        Path finalPath = greedy.findSolution();
        assertNotNull(finalPath);
    }

    @Test
    public void mediumSolutionWithEuclidean() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.MEDIUM);
        Greedy greedy = new Greedy(board, new Euclidean(new HashSet<>(board.getFinishCoordinates())));
        Path finalPath = greedy.findSolution();
        assertNotNull(finalPath);
    }

    @Test
    public void hard1SolutionWithManhattan() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD1);
        Greedy greedy = new Greedy(board, new Manhattan(new HashSet<>(board.getFinishCoordinates())));
        Path finalPath = greedy.findSolution();
        assertNotNull(finalPath);
    }

    @Test
    public void hard1SolutionWithEuclidean() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD1);
        Greedy greedy = new Greedy(board, new Euclidean(new HashSet<>(board.getFinishCoordinates())));
        Path finalPath = greedy.findSolution();
        assertNotNull(finalPath);
    }

    @Test
    public void hard2SolutionWithManhattan() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD2);
        Greedy greedy = new Greedy(board, new Manhattan(new HashSet<>(board.getFinishCoordinates())));
        Path finalPath = greedy.findSolution();
        assertNotNull(finalPath);
    }

    @Test
    public void hard2SolutionWithEuclidean() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD2);
        Greedy greedy = new Greedy(board, new Euclidean(new HashSet<>(board.getFinishCoordinates())));
        Path finalPath = greedy.findSolution();
        assertNotNull(finalPath);
    }

    @Test
    public void easySolutionWithEuclidean() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.EASY);
        Greedy greedy = new Greedy(board, new Euclidean(new HashSet<>(board.getFinishCoordinates())));
        Path finalPath = greedy.findSolution();
        assertNotNull(finalPath);
    }
}
