package com.patterndemo.kwic.service;

import com.patterndemo.kwic.core.input.Input;
import com.patterndemo.kwic.core.output.Output;
import com.patterndemo.kwic.core.piping.DataProvider;
import com.patterndemo.kwic.core.piping.DataReceiver;

public interface IndexingService {

    Input<String> getInputFromCache();

    Input<String> getInputFromBatch(String batchId);

    Output<String> getOutputToCache();

    void doIndexing(DataProvider<String> input, DataReceiver<String> output);

}
