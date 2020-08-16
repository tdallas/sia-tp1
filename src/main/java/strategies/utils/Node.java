package strategies.utils;

import game.State;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public class Node {
    private final Node parent;
    private final State state;
    private final Step step;
    private int cost;

    public void setCost(int cost) {
        this.cost = cost;
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
}