package com.patterndemo.kwic.core.filtering.impl;

import com.patterndemo.kwic.core.filtering.Filter;
import com.patterndemo.kwic.core.filtering.FilterWrapper;
import lombok.Getter;

public class AsyncFilterWrapper<T> extends FilterWrapper<T> {

    @Getter
    private Thread thread;

    public AsyncFilterWrapper(Filter<T> wrapped) {
        super(wrapped);
        setWrapped(wrapped);
    }

    @Override
    public void filter() {
        thread.start();
    }

    @Override
    public void setWrapped(Filter<T> wrapped) {
        this.wrapped = wrapped;
        this.thread = new Thread(wrapped::filter);
    }

}
