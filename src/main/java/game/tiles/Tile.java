package game.tiles;

import lombok.Getter;
import game.Coordinate;

@Getter
public abstract class Tile {
    // The coordinate in which this tile is
    // Note that this could be a dynamic field if this is a PusherTile instance
    private Coordinate coordinate;

    public abstract boolean canWalkThrough();

}
