package com.patterndemo.kwic.core.filtering.impl;

import com.patterndemo.kwic.core.filtering.Filter;
import lombok.Getter;
import lombok.Setter;

public class AsyncFilterWrapper<T> extends Filter<T> {

    @Getter
    @Setter
    private Filter<T> wrapped;

    @Getter
    private Thread thread;

    public AsyncFilterWrapper(Filter<T> wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void filter() {
        thread = new Thread(wrapped::filter);
        thread.start();
    }

}
