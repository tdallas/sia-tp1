package game;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * (x,y)
 */
@AllArgsConstructor
@Getter
public class Coordinate {

    private int x;
    private int y;

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
}
