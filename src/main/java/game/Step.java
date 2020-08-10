package game;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Step {
    private Coordinate from;
    private Coordinate to;
}