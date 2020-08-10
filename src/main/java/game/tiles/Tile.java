package game.tiles;

import game.Box;
import game.Coordinate;
import lombok.Getter;

import java.util.Objects;
import java.util.Optional;

@Getter
public abstract class Tile {
    // The coordinate in which this tile is
    // Note that this could be a dynamic field if this is a PusherTile instance
    private Coordinate coordinate;
    private Optional<Box> boxOptional = Optional.empty();

    public abstract boolean isWalkable();
    public abstract boolean isFinalTile();
    public boolean hasBox() {
        return boxOptional.isPresent();
    }
    public void setBox(Box box) {
        this.boxOptional = Optional.ofNullable(box);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return coordinate.equals(tile.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }
}
