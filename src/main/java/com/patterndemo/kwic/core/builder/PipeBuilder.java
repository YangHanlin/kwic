package com.patterndemo.kwic.core.builder;

import com.patterndemo.kwic.core.filtering.Filter;

public interface PipeBuilder<T> {

    FilterBuilder<T> addFilter(Filter<T> filter);

}
