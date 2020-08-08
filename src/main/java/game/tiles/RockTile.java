package game.tiles;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RockTile extends Tile {
    public boolean canWalkThrough() {
        return false;
    }
}
