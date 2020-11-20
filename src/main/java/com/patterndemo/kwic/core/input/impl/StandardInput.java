package com.patterndemo.kwic.core.input.impl;

import com.patterndemo.kwic.core.input.Input;

import java.util.Scanner;

public class StandardInput extends Input<String> {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getData() {
        if (scanner.hasNextLine() && state.isAllowed()) {
            return scanner.nextLine();
        }
        throw new IllegalStateException(String.format("Standard input has no more lines or current state \'%s\' is not allowed", state.getName()));
    }

}
