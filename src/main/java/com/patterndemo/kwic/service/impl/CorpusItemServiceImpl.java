package com.patterndemo.kwic.service.impl;

import com.patterndemo.kwic.entity.CorpusItem;
import com.patterndemo.kwic.repository.CorpusItemRepository;
import com.patterndemo.kwic.service.CorpusItemService;
import com.patterndemo.kwic.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CorpusItemServiceImpl implements CorpusItemService {

    @Autowired
    private CorpusItemRepository repository;

    @Autowired
    private HttpSession session;

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

    @Override
    public List<CorpusItem> getCache() {
        Object cache = session.getAttribute(Constant.CORPUS_ITEMS_CACHE_KEY);
        return cache == null ? new ArrayList<>() : (List<CorpusItem>) cache;
    }

    @Override
    public void saveCache(List<CorpusItem> items) {
        session.setAttribute(Constant.CORPUS_ITEMS_CACHE_KEY, items);
    }

}
