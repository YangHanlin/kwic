package com.patterndemo.kwic.core.output;

import com.patterndemo.kwic.core.piping.DataReceiver;
import com.patterndemo.kwic.core.piping.State;
import lombok.Getter;
import lombok.Setter;

public class StandardOutput implements DataReceiver<String> {

    @Getter
    @Setter
    private State state;

    @Override
    public void putData(String data) {
        if (!state.isAllowed()) {
            throw new IllegalStateException(String.format("State \'%s\' is not allowed", state.getName()));
        }
        System.out.println(data);
    }

}
