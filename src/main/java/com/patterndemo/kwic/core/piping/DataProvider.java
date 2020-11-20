package com.patterndemo.kwic.core.piping;

public interface DataProvider<T> extends DataComponent {

    T getData();

}
