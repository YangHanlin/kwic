package com.patterndemo.kwic.core.piping.impl;

import com.patterndemo.kwic.core.piping.AbstractPipe;
import com.patterndemo.kwic.core.piping.State;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Pipe<T> extends AbstractPipe<T> {

    private final Queue<T> dataQueue = new ArrayDeque<>();

    private final Semaphore dataSemaphore = new Semaphore(0);

    @Override
    public T getData() {
        if (dataQueue.isEmpty() && !state.isAllowed()) {
            throw new IllegalStateException(String.format("Current state \'%s\' does not allow getting data", state.getName()));
        }
        dataSemaphore.acquireUninterruptibly();
        return dataQueue.poll();
    }

    @Override
    public void putData(T data) {
        if (!state.isAllowed()) {
            throw new IllegalStateException(String.format("Current state \'%s\' does not allow putting data", state.getName()));
        }
        dataQueue.add(data);
        dataSemaphore.release();
    }
}
