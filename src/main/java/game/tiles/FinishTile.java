package game.tiles;

import game.Box;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class FinishTile extends Tile implements Walkable {

    private Optional<Box> boxOptional = Optional.empty();

    public void setBox(Box box) {
        this.boxOptional = Optional.ofNullable(box);
    }
    public boolean canWalkThrough() {
        return !boxOptional.isPresent();
    }
}
