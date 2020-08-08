package game.tiles;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmptyTile extends Tile {
    public boolean canWalkThrough() {
        return true;
    }
}
