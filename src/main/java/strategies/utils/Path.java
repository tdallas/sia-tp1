package strategies.utils;

import lombok.Getter;

import java.util.*;

@Getter
public class Path {
    private final List<Step> steps;

    public Path(Node node){
        this.steps = new LinkedList<>();
        while(node != null){
            this.steps.add(node.getStep());
            node = node.getParent();
        }
        Collections.reverse(steps);
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Path={");
        for(Step step : steps){
            stringBuilder.append(step.toString());
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
