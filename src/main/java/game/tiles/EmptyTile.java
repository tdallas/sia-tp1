package game.tiles;

import game.Coordinate;
import lombok.AllArgsConstructor;

public class EmptyTile extends Tile {

    public EmptyTile(final Coordinate coordinate)
    {
        super(coordinate);
    }

    public boolean isWalkable() {
        return true;
    }

    @Override
    public boolean isFinalTile() {
        return false;
    }

}
