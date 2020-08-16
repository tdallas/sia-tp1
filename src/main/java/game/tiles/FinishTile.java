package game.tiles;

import game.Coordinate;

public class FinishTile extends Tile {

    public FinishTile(final Coordinate coordinate) {
        super(coordinate);
    }

    public boolean isWalkable() {
        return true;
    }

    @Override
    public boolean isFinalTile() {
        return true;
    }

}
