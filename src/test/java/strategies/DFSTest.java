package strategies;

import game.Board;
import game.Step;
import org.junit.Test;
import strategies.nonInformed.DFS;

import java.util.List;

public class DFSTest {
    @Test
    public void boardGenerationTest() {
        Board board = BoardFactory.createBoard(BoardFactory.Level.MEDIUM);
        DFS dfs = new DFS(board);
        List<Step> finalPath = dfs.findSolution();
        for (int i = 0 ; i < dfs.getDirectionsDone().length ; i++) {
            if (dfs.getDirectionsDone()[i] == null) break;
            System.out.print(dfs.getDirectionsDone()[i].toString() + "->");
        }
    }
}
