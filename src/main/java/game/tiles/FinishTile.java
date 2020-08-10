package game.tiles;

import game.Box;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class FinishTile extends Tile {

    public boolean isWalkable() {
        return !getBoxOptional().isPresent();
    }

    @Override
    public boolean isFinalTile() {
        return true;
    }

}
