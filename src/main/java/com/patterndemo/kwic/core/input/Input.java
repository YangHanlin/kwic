package com.patterndemo.kwic.core.input;

import com.patterndemo.kwic.core.piping.DataProvider;
import com.patterndemo.kwic.core.piping.State;
import lombok.Getter;
import lombok.Setter;

public abstract class Input<T> implements DataProvider<T> {

    @Getter
    @Setter
    protected State state = State.OPEN;

}
