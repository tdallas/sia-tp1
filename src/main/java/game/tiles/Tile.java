package game.tiles;

import game.Box;
import lombok.Getter;
import game.Coordinate;

import java.util.Optional;

@Getter
public abstract class Tile {
    // The coordinate in which this tile is
    // Note that this could be a dynamic field if this is a PusherTile instance
    private Coordinate coordinate;
    private Optional<Box> boxOptional = Optional.empty();

    public abstract boolean isWalkable();
    public abstract boolean isFinalTile();
    public void setBox(Box box) {
        this.boxOptional = Optional.ofNullable(box);
    }

}
