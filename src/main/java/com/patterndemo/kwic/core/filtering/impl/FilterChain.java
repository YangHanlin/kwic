package com.patterndemo.kwic.core.filtering.impl;

import com.patterndemo.kwic.core.builder.FilterBuilder;
import com.patterndemo.kwic.core.filtering.Filter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class FilterChain<T> extends Filter<T> {

    @Getter
    public List<Filter<T>> filters;

    @Override
    public void filter() {
        filters.forEach(Filter::filter);
    }

    public FilterBuilder<T> builder() {
        return null;
    }

}
