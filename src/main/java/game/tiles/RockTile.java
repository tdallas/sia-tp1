package game.tiles;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RockTile extends Tile {
    public boolean isWalkable() {
        return false;
    }

    @Override
    public boolean isFinalTile() {
        return false;
    }
}
