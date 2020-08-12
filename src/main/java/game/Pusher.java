package game;

import game.tiles.Tile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Queue;

@AllArgsConstructor
@Getter
@Setter
public class Pusher {
    // FIFO structure
    private Queue<Path> path;
    private Coordinate currentCoordinate;
}
