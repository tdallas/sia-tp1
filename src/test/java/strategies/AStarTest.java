package strategies;

import game.Board;
import org.junit.Test;
import strategies.heuristics.nonTrivials.GlobalMinEuclidean;
import strategies.heuristics.nonTrivials.GlobalMinManhattan;
import strategies.heuristics.nonTrivials.ManhattanCheckingHalf;
import strategies.heuristics.almostNonTrivial.Euclidean;
import strategies.heuristics.almostNonTrivial.Manhattan;
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
    public void mediumSolutionWithGlobalMinManhattan() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.MEDIUM);
        AStar aStar = new AStar(board, new GlobalMinManhattan(board.getFinishCoordinates()));
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
    public void hardSolutionWithGlobalMinManhattan() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD);
        AStar aStar = new AStar(board, new GlobalMinManhattan(board.getFinishCoordinates()));
        Path finalPath = aStar.findSolution();
        assertNotNull(finalPath);
    }

    @Test
    public void hardSolutionWithGlobalMinEuclidean() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD);
        AStar aStar = new AStar(board, new GlobalMinEuclidean(board.getFinishCoordinates()));
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
    public void hard2SolutionWithGlobalMinManhattan() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD2);
        AStar aStar = new AStar(board, new GlobalMinManhattan(board.getFinishCoordinates()));
        Path finalPath = aStar.findSolution();
        assertNotNull(finalPath);
    }

    @Test
    public void hard2SolutionWithGlobalMinEuclidean() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD2);
        AStar aStar = new AStar(board, new GlobalMinEuclidean(board.getFinishCoordinates()));
        Path finalPath = aStar.findSolution();
        assertNotNull(finalPath);
    }


    @Test
    public void hard2SolutionWithManhattanCheckingHalf() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD2);
        AStar aStar = new AStar(board, new ManhattanCheckingHalf(board.getFinishCoordinates()));
        Path finalPath = aStar.findSolution();
        assertNotNull(finalPath);
    }

    @Test
    public void hardSolutionWithManhattanCheckingHalf() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD);
        AStar aStar = new AStar(board, new ManhattanCheckingHalf(board.getFinishCoordinates()));
        Path finalPath = aStar.findSolution();
        assertNotNull(finalPath);
    }
}
