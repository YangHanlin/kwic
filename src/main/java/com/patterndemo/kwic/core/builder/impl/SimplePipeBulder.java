package com.patterndemo.kwic.core.builder.impl;

import com.patterndemo.kwic.core.builder.FilterBuilder;
import com.patterndemo.kwic.core.builder.PipeBuilder;
import com.patterndemo.kwic.core.filtering.Filter;
import com.patterndemo.kwic.core.piping.impl.Pipe;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SimplePipeBulder<T> implements PipeBuilder<T> {

    private final List<Filter<T>> filters;

    private final Pipe<T> currentPipe;

    @Override
    public FilterBuilder<T> addFilter(Filter<T> filter) {
        filter.setDataProvider(currentPipe);
        filters.add(filter);
        return new SimpleFilterBuilder<>(filters, filter);
    }

}
