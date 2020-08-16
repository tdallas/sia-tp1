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
        this.coordinate = box.coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return label.equals(box.label) && coordinate.equals(box.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, coordinate);
    }

    @Override
    public String toString() {
        return label + "=" + coordinate;
    }
}
