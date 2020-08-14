package strategies.utils;

import game.Box;
import game.Pusher;
import game.Step;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node {
    private final Pusher pusher;
    private List<Box> boxList;

    public Node(final Pusher pusher, final List<Box> srcList) {
        this.pusher = pusher;
        this.pusher.setPath(cloneStepList(pusher.getPath()));
        this.boxList = cloneBoxList(srcList);
    }

    public List<Box> getBoxList() {
        return boxList;
    }

    public Pusher getPusher() {
        return pusher;
    }

    public boolean equals(final Node node) {
        boolean pusherEquals = this.getPusher().getCurrentCoordinate().equals(node.getPusher().getCurrentCoordinate());
        boolean boxesEquals = boxList.size() == node.getBoxList().size();
        for (int i = 0; i < boxList.size() && boxesEquals; i++) {
            boxesEquals = boxList.get(i).getCoordinate().equals(node.getBoxList().get(i).getCoordinate());
        }
        return pusherEquals && boxesEquals;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        boxList.forEach(box -> stringBuffer.append(box.toString()));
        return pusher.getCurrentCoordinate().toString() + stringBuffer.toString();
    }

    private static List<Box> cloneBoxList(List<Box> orig) {
        List<Box> clone = new ArrayList<>(Collections.nCopies(orig.size(), new Box()));
        Collections.copy(clone, orig);
        return clone;
    }

    private static List<Step> cloneStepList(List<Step> orig) {
        List<Step> clone = new ArrayList<>(Collections.nCopies(orig.size(), new Step()));
        Collections.copy(clone, orig);
        return clone;
    }

}