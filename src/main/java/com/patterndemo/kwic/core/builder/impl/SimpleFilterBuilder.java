package com.patterndemo.kwic.core.builder.impl;

import com.patterndemo.kwic.core.builder.FilterBuilder;
import com.patterndemo.kwic.core.builder.PipeBuilder;
import com.patterndemo.kwic.core.filtering.Filter;
import com.patterndemo.kwic.core.filtering.impl.FilterChain;
import com.patterndemo.kwic.core.piping.impl.Pipe;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SimpleFilterBuilder<T> implements FilterBuilder<T> {

    private final List<Filter<T>> filters;

    private final Filter<T> currentFilter;

    @Override
    public Filter<T> build() {
        return new FilterChain<>(filters);
    }

    @Override
    public PipeBuilder<T> addPipe(Pipe<T> pipe) {
        currentFilter.setDataReceiver(pipe);
        return new SimplePipeBulder<>(filters, pipe);
    }

}
