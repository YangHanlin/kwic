package com.patterndemo.kwic.core.sorting;

import com.patterndemo.kwic.core.filtering.impl.BatchFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class SimpleSorting<T extends Comparable<? super T>> extends BatchFilter<T> {

    @Getter
    @Setter
    private Comparator<T> comparator = null;

    @Override
    protected List<T> process(List<T> dataList) {
        dataList.sort(comparator);
        return dataList;
    }

}
