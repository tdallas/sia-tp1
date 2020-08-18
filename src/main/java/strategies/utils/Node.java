package strategies.utils;

import game.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public class Node {
    private final Node parent;
    private final State state;
    private final Step step;
    private final int cost;

    public Node(Node parent, State state, Step step) {
        this.parent = parent;
        this.state = state;
        this.step = step;
        this.cost = parent.cost + 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return cost == node.cost &&
                step.equals(node.step) &&
                state.equals(node.state) &&
                parent.equals(node.parent);
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

    public static Node generateNewNode(Direction direction, final Node currentNode) {
        Coordinate vectorCoordinate = Board.coordinateVectorMap.get(direction);

        Coordinate newPusher = new Coordinate(
                vectorCoordinate.getX() + currentNode.getState().getPusher().getX(),
                vectorCoordinate.getY() + currentNode.getState().getPusher().getY());

        if (currentNode.getState().getBoxes().contains(newPusher)) {
            Coordinate newBox = new Coordinate(vectorCoordinate.getX() + newPusher.getX(),
                    vectorCoordinate.getY() + newPusher.getY());
            State newState = new State(newPusher, currentNode.getState().getBoxes());
            newState.getBoxes().remove(newPusher);
            newState.getBoxes().add(newBox);
            return new Node(currentNode, newState, new Step(currentNode.getState().getPusher(), newPusher));
        }

        State newState = new State(newPusher, currentNode.getState().getBoxes());
        return new Node(currentNode, newState, new Step(currentNode.getState().getPusher(), newPusher));
    }
}