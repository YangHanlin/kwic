package com.patterndemo.kwic.core.filtering.impl;

import com.patterndemo.kwic.core.filtering.Filter;
import com.patterndemo.kwic.core.piping.State;

public abstract class SimpleFilter<T> extends Filter<T> {

    @Override
    public void filter() {
        dataReceiver.setState(State.OPEN);
        try {
            while (true) {
                T data = dataProvider.getData();
                data = process(data);
                dataReceiver.putData(data);
            }
        } catch (IllegalStateException ignored) {
        }
        dataReceiver.setState(State.CLOSED);
    }

    protected abstract T process(T data);

}
