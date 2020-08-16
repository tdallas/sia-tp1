package game.tiles;

import game.Coordinate;

public class RockTile extends Tile {

    public RockTile(final Coordinate coordinate) {
        super(coordinate);
    }

    public boolean isWalkable() {
        return false;
    }

    @Override
    public boolean isFinalTile() {
        return false;
    }
}
