package strategies;

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

    private long startTime = 0;
    private long finishTime = 0;

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
