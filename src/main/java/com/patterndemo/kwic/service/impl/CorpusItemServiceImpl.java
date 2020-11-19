package com.patterndemo.kwic.service.impl;

import com.patterndemo.kwic.entity.CorpusItem;
import com.patterndemo.kwic.repository.CorpusItemRepository;
import com.patterndemo.kwic.service.CorpusItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CorpusItemServiceImpl implements CorpusItemService {

    @Autowired
    private CorpusItemRepository repository;

    @Override
    public List<CorpusItem> getBatch(String batchId) {
        return repository.findByBatchId(batchId);
    }

    @Override
    public void saveBatch(String batchId, List<CorpusItem> items) {
        items.forEach(item -> item.setBatchId(batchId));
        repository.saveAll(items);
    }

    @Override
    public String saveBatch(List<CorpusItem> items) {
        String batchId = UUID.randomUUID().toString();
        saveBatch(batchId, items);
        return batchId;
    }
}
