package game;

import game.tiles.Tile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Grid {
    private List<List<Tile>> matrix;
    private int rowSize;
    private int columnSize;
}
