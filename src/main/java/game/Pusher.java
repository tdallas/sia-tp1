package game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pusher implements Cloneable {
    private List<Step> path;
    private Coordinate currentCoordinate;

    public Pusher(final Pusher pusher) {
        this.path = pusher.getPath();
        this.currentCoordinate = pusher.getCurrentCoordinate();
    }

    public void addStep(final Step step) {
        // This is done because of backtracking consistency issues
        List<Step> newPath = new ArrayList<>(path);
        Collections.copy(newPath, path);
        newPath.add(step);
        this.path = newPath;
    }

}
