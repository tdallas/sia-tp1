package game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class Box {
    private String label;
    private Coordinate coordinate;

    public Box(Box box){
        this.label = box.label;
        this.coordinate = new Coordinate(box.coordinate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return Objects.equals(label, box.label) &&
                Objects.equals(coordinate, box.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, coordinate);
    }

    public String toString() {
        return label + "=" + coordinate.toString();
    }
}
