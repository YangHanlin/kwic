package com.patterndemo.kwic.core.piping;

import lombok.Getter;
import lombok.Setter;

public abstract class AbstractPipe<T> implements DataProvider<T>, DataReceiver<T> {

    @Getter
    @Setter
    protected State state = State.CLOSED;

}
