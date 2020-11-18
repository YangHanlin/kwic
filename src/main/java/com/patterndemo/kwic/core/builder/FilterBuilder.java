package com.patterndemo.kwic.core.builder;

import com.patterndemo.kwic.core.filtering.Filter;
import com.patterndemo.kwic.core.piping.impl.Pipe;

public interface FilterBuilder<T> {

    Filter<T> build();

    PipeBuilder<T> addPipe(Pipe<T>  pipe);

}
