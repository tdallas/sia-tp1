package strategies.utils;

import game.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public class Step {
    private final Coordinate from;
    private final Coordinate to;

    public String toString() {
        return from.toString() + "->" + to.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return from.equals(step.from) && to.equals(step.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}