package com.patterndemo.kwic.core.util;

import com.patterndemo.kwic.core.filtering.impl.SimpleFilter;

import java.util.ArrayList;
import java.util.List;

public class RemoveBlankLineFilter extends SimpleFilter<String> {

    @Override
    protected List<String> process(String data) {
        List<String> res = new ArrayList<>();
        if (data != null && !data.isBlank()) {
            res.add(data);
        }
        return res;
    }
}
