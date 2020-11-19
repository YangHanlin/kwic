package com.patterndemo.kwic.core.output;

public class StandardOutput extends Output<String> {

    @Override
    public void putData(String data) {
        if (!state.isAllowed()) {
            throw new IllegalStateException(String.format("State \'%s\' is not allowed", state.getName()));
        }
        System.out.println(data);
    }

}
