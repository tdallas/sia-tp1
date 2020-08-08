package strategies;

import lombok.Getter;

@Getter
public class StateTree {

    private State initialState;
    private State currentState;

    public StateTree(final State initialState) {
        this.initialState = initialState;
    }

    public State setCurrentState(final State currentState) {
        this.currentState = currentState;
        return this.currentState;
    }
}
