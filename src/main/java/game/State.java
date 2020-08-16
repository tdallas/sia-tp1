package game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import strategies.utils.Node;

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
        this.boxes.addAll(state.getBoxes());
    }

    public State(Pusher pusher, Set<Box> boxes, Box newBox) {
        this.pusher = pusher;
        this.boxes = new HashSet<>();
        for (Box box : boxes) {
            // with this we avoid references problems
            if (box.getLabel().equals(newBox.getLabel())) {
                this.boxes.add(new Box(newBox));
            } else {
                this.boxes.add(box);
            }
        }
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
