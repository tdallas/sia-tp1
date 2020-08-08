package game.tiles;

import game.Box;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class EmptyTile extends Tile implements Walkable{

    private Optional<Box> boxOptional = Optional.empty();

    /**
     * box must not be null
     * @param box
     */
    public void setBox(final Box box) {
        this.boxOptional = Optional.ofNullable(box);
    }
    public boolean canWalkThrough() {
        return !boxOptional.isPresent();
    }
}
