package com.patterndemo.kwic.core.filtering.impl;

import com.patterndemo.kwic.core.filtering.Filter;
import lombok.Getter;

public class AsyncFilterWrapper<T> extends Filter<T> {

    @Getter
    private Filter<T> wrapped;

    @Getter
    private Thread thread;

    public AsyncFilterWrapper(Filter<T> wrapped) {
        setWrapped(wrapped);
    }

    @Override
    public void filter() {
        thread.start();
    }

    public void setWrapped(Filter<T> wrapped) {
        this.wrapped = wrapped;
        this.thread = new Thread(wrapped::filter);
    }

}
