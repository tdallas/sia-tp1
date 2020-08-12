package game;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
@AllArgsConstructor
public class Path {
    private List<Step> steps = new LinkedList<>();

    public Path addStep(final Step step) {
        LinkedList<Step> newStepList = new LinkedList<>(steps);
        newStepList.add(step);
        return new Path(newStepList);
    }
}
