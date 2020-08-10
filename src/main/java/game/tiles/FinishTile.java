package game.tiles;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FinishTile extends Tile {

    public boolean isWalkable() {
        return !getBoxOptional().isPresent();
    }

    @Override
    public boolean isFinalTile() {
        return true;
    }

}
