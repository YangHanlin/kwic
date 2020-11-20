package com.patterndemo.kwic.controller;

import com.patterndemo.kwic.dto.BatchIdResult;
import com.patterndemo.kwic.dto.IndexedResultList;
import com.patterndemo.kwic.entity.IndexedResult;
import com.patterndemo.kwic.service.IndexedResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
public class IndexedResultController {

    @Autowired
    private IndexedResultService service;

    @GetMapping("/batch/{batchId}")
    public IndexedResultList getBatch(@PathVariable String batchId) {
        List<IndexedResult> results = service.getBatch(batchId);
        return new IndexedResultList(results);
    }

    @PostMapping("/batch")
    public BatchIdResult saveBatch(@RequestBody IndexedResultList results) {
        String batchId = service.saveBatch(results.getResults());
        return new BatchIdResult(batchId);
    }

    @GetMapping("/cache")
    public IndexedResultList getCache() {
        List<IndexedResult> results = service.getCache();
        return new IndexedResultList(results);
    }

    @PostMapping("/cache/persistence")
    public BatchIdResult persistCache() {
        String batchId = service.saveBatch(service.getCache());
        return new BatchIdResult(batchId);
    }

}
