package com.patterndemo.kwic.service.impl;

import com.patterndemo.kwic.core.builder.BuilderFactory;
import com.patterndemo.kwic.core.builder.impl.SimpleBuilderFactory;
import com.patterndemo.kwic.core.filtering.Filter;
import com.patterndemo.kwic.core.input.Input;
import com.patterndemo.kwic.core.input.impl.ListInput;
import com.patterndemo.kwic.core.output.Output;
import com.patterndemo.kwic.core.piping.DataProvider;
import com.patterndemo.kwic.core.piping.DataReceiver;
import com.patterndemo.kwic.core.piping.impl.Pipe;
import com.patterndemo.kwic.core.shifting.SimpleShift;
import com.patterndemo.kwic.core.sorting.SimpleSorting;
import com.patterndemo.kwic.core.util.IgnoreCaseComparator;
import com.patterndemo.kwic.core.util.RemoveBlankLineFilter;
import com.patterndemo.kwic.entity.CorpusItem;
import com.patterndemo.kwic.entity.IndexedResult;
import com.patterndemo.kwic.service.CorpusItemService;
import com.patterndemo.kwic.service.IndexedResultService;
import com.patterndemo.kwic.service.IndexingService;
import com.patterndemo.kwic.util.IndexedResultOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexingServiceImpl implements IndexingService {

    @Autowired
    CorpusItemService corpusItemService;

    @Autowired
    IndexedResultService indexedResultService;

    private Input<String> getInputFromItems(List<CorpusItem> items) {
        List<String> lines = items.stream()
                .flatMap(item -> Arrays.stream(item.getContent().split("\n")))
                .collect(Collectors.toList());
        return new ListInput<>(lines);
    }

    @Override
    public Input<String> getInputFromCache() {
        List<CorpusItem> items = corpusItemService.getCache();
        return getInputFromItems(items);
    }

    @Override
    public Input<String> getInputFromBatch(String batchId) {
        List<CorpusItem> items = corpusItemService.getBatch(batchId);
        return getInputFromItems(items);
    }

    @Override
    public Output<String> getOutputToCache() {
        List<IndexedResult> results = new ArrayList<>();
        indexedResultService.saveCache(results);
        return new IndexedResultOutput(results);
    }

    @Override
    public void doIndexing(DataProvider<String> input, DataReceiver<String> output) {
        BuilderFactory<String> factory = new SimpleBuilderFactory<>();
        Filter<String> filter = factory
                .fromSingleFilter(new RemoveBlankLineFilter())
                .addPipe(new Pipe<>())
                .addFilter(new SimpleShift())
                .addPipe(new Pipe<>())
                .addFilter(new SimpleSorting<>(new IgnoreCaseComparator()))
                .build();
        filter.setDataProvider(input);
        filter.setDataReceiver(output);
        filter.filter();
    }
}
