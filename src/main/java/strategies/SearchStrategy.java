package strategies;

import game.Board;
import lombok.Getter;
import lombok.Setter;
import strategies.utils.Path;

/**
 * I think this could be an interface also, it gets a little confusing for me if SearchStrategy is
 * an object or an object's behaviour tbh
 */
@Getter
@Setter
public abstract class SearchStrategy {

    private final Board board;
    protected long expandedNodes;
    protected long borderNodes;
    private long startTime;
    private long finishTime;

    public SearchStrategy(final Board board){
        this.board = board;
        this.startTime = 0;
        this.finishTime = 0;
        this.expandedNodes = 0;
        this.borderNodes = 0;
    }

    /**
     * This function find a solution for a Sokoban initial board
     * @return
     */
    public abstract Path findSolution();

    public long getSolveTime(){
        if(startTime > 0 && finishTime > 0){
            return finishTime - startTime;
        }
        return 0;
    }
}
