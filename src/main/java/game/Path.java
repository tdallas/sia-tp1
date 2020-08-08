package game;

import game.tiles.Tile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import strategies.Direction;

@AllArgsConstructor
@Getter
public class Path {
    private Tile currentTile;
    private Tile nextTile;
    private Direction nextDirection;
}
