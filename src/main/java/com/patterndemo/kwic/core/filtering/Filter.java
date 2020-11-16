package com.patterndemo.kwic.core.filtering;

import com.patterndemo.kwic.core.piping.DataProvider;
import com.patterndemo.kwic.core.piping.DataReceiver;
import lombok.Setter;

public abstract class Filter<T> {

    @Setter
    protected DataProvider<T> dataProvider;

    @Setter
    protected DataReceiver<T> dataReceiver;

    public abstract void filter();

}
