package strategies;

import game.Board;
import game.BoardFactory;
import org.junit.Test;
import strategies.heuristics.almostNonTrivial.Euclidean;
import strategies.heuristics.almostNonTrivial.Manhattan;
import strategies.heuristics.nonTrivials.GlobalMinManhattan;
import strategies.informed.IDAStar;
import strategies.utils.Path;

import java.util.HashSet;

public class IDAStarTest {
    @Test
    public void hard1SolutionWithManhattan() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.HARD1);
        IDAStar IDAStar = new IDAStar(board, new Manhattan(new HashSet<>(board.getFinishCoordinates())));
        Path finalPath = IDAStar.findSolution();
        System.out.println(finalPath);
    }
}
