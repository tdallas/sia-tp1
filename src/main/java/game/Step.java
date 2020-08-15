package game;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public class Step {
    private Coordinate from;
    private Coordinate to;

    public Step() {}

    public Step(Step step){
        this.from = new Coordinate(from);
        this.to = new Coordinate(to);
    }

    public String toString() {
        return from.toString() + "->" + to.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return Objects.equals(from, step.from) &&
                Objects.equals(to, step.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}