package game.tiles;

import game.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public abstract class Tile {
    // The coordinate in which this tile is
    // Note that this could be a dynamic field if this is a PusherTile instance
    private Coordinate coordinate;

    public abstract boolean isWalkable();
    public abstract boolean isFinalTile();

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
