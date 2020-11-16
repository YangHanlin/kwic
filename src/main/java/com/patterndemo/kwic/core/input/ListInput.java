package com.patterndemo.kwic.core.input;

import java.util.Iterator;
import java.util.List;

public class ListInput<T> extends Input<T> {

    private final List<T> list;

    private final Iterator<T> iterator;

    public ListInput(List<T> list) {
        this.list = list;
        this.iterator = list.iterator();
    }

    @Override
    public T getData() {
        if (!state.isAllowed()) {
            throw new IllegalStateException(String.format("Current state '%s' is not allowed", state.getName()));
        }
        if (!iterator.hasNext()) {
            throw new IllegalStateException("Iterator has reached end of list");
        }
        return iterator.next();
    }

}
