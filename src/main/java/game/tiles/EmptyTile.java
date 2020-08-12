package game.tiles;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmptyTile extends Tile {

    public boolean isWalkable() {
        return true;
    }

    @Override
    public boolean isFinalTile() {
        return false;
    }

}
