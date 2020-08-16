package game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pusher{
    private Coordinate coordinate;

    public Pusher(Pusher pusher) {
        this.coordinate = pusher.coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pusher pusher = (Pusher) o;
        return Objects.equals(coordinate, pusher.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }

    @Override
    public String toString() {
        return "Pusher=" + coordinate;
    }
}
