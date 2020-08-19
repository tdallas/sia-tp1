package strategies.utils;

import lombok.Getter;

import java.util.*;

@Getter
public class Path {
    private Node node;
    private final List<Step> steps;

    public Path(Node node){
        this.node = node;
        this.steps = new LinkedList<>();
        while(node != null){
            if(node.getStep() != null) {
                this.steps.add(node.getStep());
            }
            node = node.getParent();
        }
        Collections.reverse(steps);
    }

    public int getLength(){
        return steps.size();
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
        stringBuilder.append("Path Length=" + steps.size() +", Path={");
        for(Step step : steps){
            stringBuilder.append(step.toString());
            stringBuilder.append(", ");
        }
        if(!steps.isEmpty()) {
            stringBuilder.setLength(stringBuilder.length() - 2);
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
