package com.patterndemo.kwic.core.filtering.impl;

import com.patterndemo.kwic.core.filtering.Filter;
import com.patterndemo.kwic.core.piping.State;

import java.util.ArrayList;
import java.util.List;

public abstract class BatchFilter<T> extends Filter<T> {

    @Override
    public void filter() {
        dataReceiver.setState(State.OPEN);
        List<T> dataList = new ArrayList<>();
        try {
            while (true) {
                dataList.add(dataProvider.getData());
            }
        } catch (IllegalStateException ignored) {
        }
        process(dataList).forEach(dataReceiver::putData);
        dataReceiver.setState(State.CLOSED);
    }

    protected abstract List<T> process(List<T> dataList);

}
