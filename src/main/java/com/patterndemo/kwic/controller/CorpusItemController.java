package com.patterndemo.kwic.controller;

import com.patterndemo.kwic.dto.BatchIdResult;
import com.patterndemo.kwic.dto.CorpusItemList;
import com.patterndemo.kwic.entity.CorpusItem;
import com.patterndemo.kwic.service.CorpusItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/corpus")
public class CorpusItemController {

    @Autowired
    private CorpusItemService service;

    @GetMapping("/batch/{batchId}")
    public CorpusItemList getBatch(@PathVariable String batchId) {
        List<CorpusItem> items = service.getBatch(batchId);
        return new CorpusItemList(items);
    }

    @PostMapping("/batch")
    public BatchIdResult saveBatch(@RequestBody CorpusItemList items) {
        String batchId = service.saveBatch(items.getItems());
        return new BatchIdResult(batchId);
    }

}
