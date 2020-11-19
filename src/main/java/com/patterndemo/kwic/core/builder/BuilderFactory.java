package com.patterndemo.kwic.core.builder;

import com.patterndemo.kwic.core.filtering.Filter;

public interface BuilderFactory<T> {

    FilterBuilder<T> fromSingleFilter(Filter<T> filter);

}
