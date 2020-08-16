package strategies.utils;

import game.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class Node {
    private final Node parent;
    private final State state;
    private Step step;
    private int cost;

    public Node(Node parent, State state){
        this.parent = parent;
        this.state = state;
        this.step = null;
        this.cost = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return cost == node.cost &&
                Objects.equals(parent, node.parent) &&
                Objects.equals(state, node.state) &&
                Objects.equals(step, node.step);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, state, step, cost);
    }

    @Override
    public String toString() {
        return "Node{" +
                "state=" + state +
                ", step=" + step +
                '}';
    }
}