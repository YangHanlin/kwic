package com.patterndemo.kwic.core.builder.impl;

import com.patterndemo.kwic.core.builder.BuilderFactory;
import com.patterndemo.kwic.core.builder.FilterBuilder;
import com.patterndemo.kwic.core.filtering.Filter;

import java.util.ArrayList;
import java.util.Collections;

public class SimpleBuilderFactory<T> implements BuilderFactory<T> {

    @Override
    public FilterBuilder<T> fromSingleFilter(Filter<T> filter) {
        return new SimpleFilterBuilder<>(new ArrayList<>(Collections.singletonList(filter)), filter);
    }

}
