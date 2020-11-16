package com.patterndemo.kwic.core.filtering.impl;

import com.patterndemo.kwic.core.filtering.Filter;

public class PlaceHolderFilter<T> extends Filter<T> {

    @Override
    protected T process(T data) {
        return data;
    }

}
