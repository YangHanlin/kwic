package com.patterndemo.kwic.core.piping;

public interface DataComponent {

    State getState();

    void setState(State state);

}
