package game;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
@AllArgsConstructor
public class Path {
    private List<Step> steps;

    public Path(){
        this.steps = new LinkedList<>();
    }

    public Path(Path path){
        this.steps = new LinkedList<>();
        this.steps.addAll(path.steps);
    }

    public void addStep(final Step step){
        steps.add(step);
    }

}
