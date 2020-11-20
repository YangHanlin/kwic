package com.patterndemo.kwic.core.filtering.impl;

import com.patterndemo.kwic.core.builder.FilterBuilder;
import com.patterndemo.kwic.core.builder.impl.SimpleFilterBuilder;
import com.patterndemo.kwic.core.filtering.Filter;
import com.patterndemo.kwic.core.piping.DataProvider;
import com.patterndemo.kwic.core.piping.DataReceiver;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class FilterChain<T> extends Filter<T> {

    @Getter
    public List<Filter<T>> filters;

    @Override
    public void setDataProvider(DataProvider<T> dataProvider) {
        super.setDataProvider(dataProvider);
        if (!filters.isEmpty()) {
            filters.get(0).setDataProvider(dataProvider);
        }
    }

    @Override
    public void setDataReceiver(DataReceiver<T> dataReceiver) {
        super.setDataReceiver(dataReceiver);
        if (!filters.isEmpty()) {
            filters.get(filters.size() - 1).setDataReceiver(dataReceiver);
        }
    }

    @Override
    public void filter() {
        filters.forEach(Filter::filter);
    }

    public FilterBuilder<T> builder() {
        return new SimpleFilterBuilder<>(filters, filters.get(filters.size() - 1));
    }

}
