package game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import strategies.utils.Node;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
public class State {
    private Coordinate pusher;
    private Set<Coordinate> boxes;

    public State(Coordinate pusher, Set<Coordinate> boxes) {
        this.pusher = pusher;
        this.boxes = new HashSet<>();
        this.boxes.addAll(boxes);
    }

    public State(State state){
        this.pusher = state.pusher;
        this.boxes = new HashSet<>();
        this.boxes.addAll(state.boxes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return pusher.equals(state.pusher) && boxes.containsAll(state.boxes) && state.boxes.containsAll(boxes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pusher, boxes);
    }

    @Override
    public String toString() {
        return pusher + ", Boxes={" + boxes + '}';
    }
}
