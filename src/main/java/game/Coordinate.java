package game;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public class Coordinate {

    private final int x;
    private final int y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public static Coordinate add(final Coordinate fromCoordinate, final Coordinate coordinateToAdd) {
        return new Coordinate(fromCoordinate.getX() + coordinateToAdd.getX(), fromCoordinate.getY() + coordinateToAdd.getY());
    }

    @Override
    public String toString() {
        return "[" + getX() + "," + getY() + "]";
    }
}
