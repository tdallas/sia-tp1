package game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class State {
    private Pusher pusher;
    private Set<Box> boxes;

    public State(State state){
        this.pusher = new Pusher(state.pusher);
        this.boxes = new HashSet<>();
        for(Box box : state.boxes){
            this.boxes.add(new Box(box));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return pusher.equals(state.pusher) && boxes.equals(state.boxes);
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
