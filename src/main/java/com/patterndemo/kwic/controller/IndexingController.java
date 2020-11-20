package com.patterndemo.kwic.controller;

import com.patterndemo.kwic.core.input.Input;
import com.patterndemo.kwic.core.output.Output;
import com.patterndemo.kwic.service.IndexingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/indexing")
public class IndexingController {

    @Autowired
    IndexingService service;

    @PostMapping("/cache")
    public void indexFromCache(HttpServletResponse response) {
        Input<String> input = service.getInputFromCache();
        Output<String> output = service.getOutputToCache();
        service.doIndexing(input, output);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @PostMapping("/corpus/{batchId}")
    public void indexFromCorpus(@PathVariable String batchId, HttpServletResponse response) {
        Input<String> input = service.getInputFromBatch(batchId);
        Output<String> output = service.getOutputToCache();
        service.doIndexing(input, output);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

}
