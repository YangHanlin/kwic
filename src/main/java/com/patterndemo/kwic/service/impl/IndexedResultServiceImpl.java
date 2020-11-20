package com.patterndemo.kwic.service.impl;

import com.patterndemo.kwic.entity.CorpusItem;
import com.patterndemo.kwic.entity.IndexedResult;
import com.patterndemo.kwic.repository.IndexedResultRepository;
import com.patterndemo.kwic.service.IndexedResultService;
import com.patterndemo.kwic.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class IndexedResultServiceImpl implements IndexedResultService {

    @Autowired
    private IndexedResultRepository repository;

    @Autowired
    private HttpSession session;

    @Override
    public List<IndexedResult> getBatch(String batchId) {
        return repository.findByBatchId(batchId);
    }

    @Override
    public void saveBatch(String batchId, List<IndexedResult> results) {
        results.forEach(result -> result.setBatchId(batchId));
        repository.saveAll(results);
    }

    @Override
    public String saveBatch(List<IndexedResult> results) {
        String batchId = UUID.randomUUID().toString();
        saveBatch(batchId, results);
        return batchId;
    }

    @Override
    public List<IndexedResult> getCache() {
        Object cache = session.getAttribute(Constant.INDEXED_RESULTS_CACHE_KEY);
        return cache == null ? new ArrayList<>() : (List<IndexedResult>) cache;
    }

    @Override
    public void saveCache(List<IndexedResult> results) {
        session.setAttribute(Constant.INDEXED_RESULTS_CACHE_KEY, results);
    }
}
