package game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pusher implements Cloneable {
    private Path path;
    private Coordinate currentCoordinate;

    public Pusher(final Pusher pusher) {
        this.path = new Path(pusher.path);
        this.currentCoordinate = new Coordinate(pusher.currentCoordinate);
    }

    public void addStep(final Step step) {
        // This is done because of backtracking consistency issues
        this.path = new Path(path);
        path.addStep(step);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pusher pusher = (Pusher) o;
        return Objects.equals(currentCoordinate, pusher.currentCoordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentCoordinate);
    }
}
