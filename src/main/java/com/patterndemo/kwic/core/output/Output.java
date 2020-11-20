package com.patterndemo.kwic.core.output;

import com.patterndemo.kwic.core.piping.DataReceiver;
import com.patterndemo.kwic.core.piping.State;
import lombok.Getter;
import lombok.Setter;

public abstract class Output<T> implements DataReceiver<T> {

    @Getter
    @Setter
    protected State state = State.OPEN;

}
