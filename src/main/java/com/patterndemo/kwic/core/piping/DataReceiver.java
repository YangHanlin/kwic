package com.patterndemo.kwic.core.piping;

public interface DataReceiver<T> extends DataComponent {

    void putData(T data);

}
