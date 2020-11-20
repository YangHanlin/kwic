package com.patterndemo.kwic.core.filtering.impl;

import com.patterndemo.kwic.core.filtering.Filter;
import com.patterndemo.kwic.core.piping.State;

import java.util.List;

public abstract class SimpleFilter<T> extends Filter<T> {

    @Override
    public void filter() {
        dataReceiver.setState(State.OPEN);
        try {
            while (true) {
                process(dataProvider.getData()).forEach(dataReceiver::putData);
            }
        } catch (IllegalStateException ignored) {
        }
        dataReceiver.setState(State.CLOSED);
    }

    protected abstract List<T> process(T data);

}
