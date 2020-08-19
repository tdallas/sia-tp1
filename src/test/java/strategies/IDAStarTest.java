package strategies;

import game.Board;
import game.BoardFactory;
import org.junit.Test;
import strategies.heuristics.almostNonTrivial.Manhattan;
import strategies.heuristics.nonTrivials.GlobalMinEuclidean;
import strategies.informed.IDAStar;
import strategies.utils.Path;

import java.util.HashSet;

public class IDAStarTest {
    @Test
    public void mediumSolutionWithManhattan() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD1);
        IDAStar IDAStar = new IDAStar(board, new Manhattan(new HashSet<>(board.getFinishCoordinates())));
        Path finalPath = IDAStar.findSolution();
        System.out.println(finalPath);
    }
}
