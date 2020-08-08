package game.tiles;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoxtTile extends Tile {
    public boolean canWalkThrough() {
        return false;
    }
}
