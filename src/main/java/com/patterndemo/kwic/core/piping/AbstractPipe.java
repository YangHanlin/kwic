package com.patterndemo.kwic.core.piping;

public abstract class AbstractPipe<T> implements DataProvider<T>, DataReceiver<T> {

    protected State state = State.CLOSED;

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

}
