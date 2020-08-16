package strategies.utils;

import game.Box;
import game.Pusher;
import game.Step;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Node {
    private final Pusher pusher;
    private List<Box> boxList;

    public Node(final Pusher pusher, final List<Box> boxList) {
        this.pusher = new Pusher(pusher);
        this.boxList = new ArrayList<>();
        for(Box box : boxList){
            this.boxList.add(new Box(box));
        }
    }

    public List<Box> getBoxList() {
        return boxList;
    }

    public Pusher getPusher() {
        return pusher;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        boxList.forEach(box -> stringBuffer.append(box.toString()));
        return "Pusher=" + pusher.getCurrentCoordinate().toString() + stringBuffer.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(pusher, node.pusher) &&
                Objects.equals(boxList, node.boxList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pusher, boxList);
    }
}