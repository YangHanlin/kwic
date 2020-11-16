package com.patterndemo.kwic.core.filtering.impl;

public class PlaceHolderFilter<T> extends SimpleFilter<T> {

    @Override
    protected T process(T data) {
        return data;
    }

}
