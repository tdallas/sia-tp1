package strategies;

import game.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class SearchTree {
    private Board current;
    private List<Board> children;

    // I dont know if this should be place here but for now we wont use it
    private int cost;

    public SearchTree(final Board current, final List<Board> children) {
        this.current = current;
        this.children = children;
    }
}
