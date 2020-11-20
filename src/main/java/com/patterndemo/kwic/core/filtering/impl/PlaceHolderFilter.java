package com.patterndemo.kwic.core.filtering.impl;

import java.util.Collections;
import java.util.List;

public class PlaceHolderFilter<T> extends SimpleFilter<T> {

    @Override
    protected List<T> process(T data) {
        return Collections.singletonList(data);
    }

}
