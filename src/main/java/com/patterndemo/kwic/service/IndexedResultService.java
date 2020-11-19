package com.patterndemo.kwic.service;

import com.patterndemo.kwic.entity.IndexedResult;

import java.util.List;

public interface IndexedResultService {

    List<IndexedResult> getBatch(String batchId);

    void saveBatch(String batchId, List<IndexedResult> results);

    String saveBatch(List<IndexedResult> results);

}
