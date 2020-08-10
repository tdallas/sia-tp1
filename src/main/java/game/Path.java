package game;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class Path {
    private Board startState;
    private List<Step> steps = new LinkedList<>();

    public Path(final Board startState) {
        this.startState = startState;
    }

    public void addStep(final Step step) {
        steps.add(step);
    }
}
