package com.patterndemo.kwic.core.shifting;

import com.patterndemo.kwic.core.filtering.impl.SimpleFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleShift extends SimpleFilter<String> {

    @Override
    protected List<String> process(String data) {
        // FIXME: Current method of splitting words does not preserve original spaces
        List<String> res = new ArrayList<>();
        String[] words = data.split("\\s+");
        for (int i = 0; i < words.length; ++i) {
            res.add(reassemble(words, i));
        }
        return res;
    }

    private String reassemble(String[] words, int startIndex) {
        List<String> reorderedWords = Stream
                .of(Arrays.copyOfRange(words, startIndex, words.length), Arrays.copyOfRange(words, 0, startIndex))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
        return String.join(" ", reorderedWords);
    }

}
