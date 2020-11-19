package com.patterndemo.kwic.service.impl;

import com.patterndemo.kwic.entity.IndexedResult;
import com.patterndemo.kwic.repository.IndexedResultRepository;
import com.patterndemo.kwic.service.IndexedResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexedResultServiceImpl implements IndexedResultService {

    @Autowired
    private IndexedResultRepository repository;

    @Override
    public List<IndexedResult> getBatch(String batchId) {
        return repository.findByBatchId(batchId);
    }

    @Override
    public void saveBatch(String batchId, List<IndexedResult> results) {
        results.forEach(result -> result.setBatchId(batchId));
        repository.saveAll(results);
    }
}
