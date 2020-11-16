package com.patterndemo.kwic.core.filtering;

import com.patterndemo.kwic.core.piping.DataProvider;
import com.patterndemo.kwic.core.piping.DataReceiver;
import com.patterndemo.kwic.core.piping.State;
import lombok.Setter;

public abstract class Filter<T> {

    @Setter
    protected DataProvider<T> dataProvider;

    @Setter
    protected DataReceiver<T> dataReceiver;

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
