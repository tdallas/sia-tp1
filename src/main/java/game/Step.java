package game;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Step {
    private Coordinate from;
    private Coordinate to;

    public Step() {}

    public String toString() {
        return from.toString() + "->" + to.toString();
    }
}