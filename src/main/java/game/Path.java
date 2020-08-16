package game;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path path = (Path) o;
        return Objects.equals(steps, path.steps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(steps);
    }
}
