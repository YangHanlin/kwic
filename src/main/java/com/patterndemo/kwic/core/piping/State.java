package com.patterndemo.kwic.core.piping;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum State {

    /**
     * Open state, in which data can be received or provided
     */
    OPEN("open", true),

    /**
     * Closed state, in which data cannot be received or provided
     */
    CLOSED("closed", false),
    ;

    private String name;

    private boolean allowed;

}
