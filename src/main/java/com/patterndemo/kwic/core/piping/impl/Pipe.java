package com.patterndemo.kwic.core.piping.impl;

import com.patterndemo.kwic.core.piping.AbstractPipe;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Pipe<T> extends AbstractPipe<T> {

    private final Queue<T> dataQueue = new ArrayDeque<>();

    private final Semaphore dataSemaphore = new Semaphore(0);

    private final Lock dataLock = new ReentrantLock();

    @Override
    public T getData() {
        dataLock.lock();
        try {
            if (dataQueue.isEmpty() && !state.isAllowed()) {
                throw new IllegalStateException(String.format("Current state \'%s\' does not allow getting data", state.getName()));
            }
            dataSemaphore.acquireUninterruptibly();
            return dataQueue.poll();
        } finally {
            dataLock.unlock();
        }
    }

    @Override
    public void putData(T data) {
        dataLock.lock();
        try {
            if (!state.isAllowed()) {
                throw new IllegalStateException(String.format("Current state \'%s\' does not allow putting data", state.getName()));
            }
            dataQueue.add(data);
            dataSemaphore.release();
        } finally {
            dataLock.unlock();
        }
    }
}
