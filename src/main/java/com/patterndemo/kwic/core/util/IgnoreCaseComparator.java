package com.patterndemo.kwic.core.util;

import java.util.Comparator;

public class IgnoreCaseComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.toLowerCase().compareTo(o2.toLowerCase());
    }
}
