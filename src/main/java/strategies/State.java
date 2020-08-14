package strategies;

import game.tiles.Tile;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class State {
    private Tile currentTile;
    private Map<Direction, Tile> nextStates = new HashMap<Direction, Tile>();

    public State(final Tile currentTile) {
        this.currentTile = currentTile;
    }
}
