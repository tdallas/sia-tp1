package strategies.utils;

import game.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class Node {
    private final Node parent;
    private State state;
    private Step step;
    private int cost;

    public Node(Node parent, State state, Step step) {
        this.parent = parent;
        this.state = state;
        this.step = step;
        this.cost = 0;
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

    public static Node generateNewNode(Direction direction, final Node currentNode, final Board board) {
        Coordinate vectorCoordinate = Board.coordinateVectorMap.get(direction);
        Pusher newPusher = new Pusher(new Coordinate(
                vectorCoordinate.getX() + currentNode.getState().getPusher().getCoordinate().getX(),
                vectorCoordinate.getY() + currentNode.getState().getPusher().getCoordinate().getY()));
        Box box = board.getBoxInCoordinate(newPusher.getCoordinate(), currentNode.getState());
        // check whether is a box in that coordinate, if its, move it too and generate new node
        if (box != null) {
            Box newBox = new Box(box.getLabel(), new Coordinate(vectorCoordinate.getX() + box.getCoordinate().getX(),
                    vectorCoordinate.getY() + box.getCoordinate().getY()));
            State newState = new State(newPusher, currentNode.getState().getBoxes(), newBox);
            return new Node(currentNode, newState, new Step(currentNode.getState().getPusher().getCoordinate(), newPusher.getCoordinate()));
        }
        State newState = new State(newPusher, Set.copyOf(currentNode.getState().getBoxes()));
        return new Node(currentNode, newState, new Step(currentNode.getState().getPusher().getCoordinate(), newPusher.getCoordinate()));
    }
}