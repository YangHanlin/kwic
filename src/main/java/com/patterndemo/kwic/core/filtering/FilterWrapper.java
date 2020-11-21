package com.patterndemo.kwic.core.filtering;

import com.patterndemo.kwic.core.piping.DataProvider;
import com.patterndemo.kwic.core.piping.DataReceiver;
import lombok.Getter;
import lombok.Setter;

public abstract class FilterWrapper<T> extends Filter<T> {

    @Getter
    @Setter
    protected Filter<T> wrapped;

    public FilterWrapper(Filter<T> wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void filter() {
        wrapped.filter();
    }

    @Override
    public void setDataProvider(DataProvider<T> dataProvider) {
        super.setDataProvider(dataProvider);
        wrapped.setDataProvider(dataProvider);
    }

    @Override
    public void setDataReceiver(DataReceiver<T> dataReceiver) {
        super.setDataReceiver(dataReceiver);
        wrapped.setDataReceiver(dataReceiver);
    }
}
