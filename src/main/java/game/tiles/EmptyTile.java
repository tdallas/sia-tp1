package game.tiles;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmptyTile extends Tile {

    public boolean isWalkable() {
        return !getBoxOptional().isPresent();
    }

    @Override
    public boolean isFinalTile() {
        return false;
    }

}
