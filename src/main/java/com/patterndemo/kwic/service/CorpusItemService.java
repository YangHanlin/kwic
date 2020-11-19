package com.patterndemo.kwic.service;

import com.patterndemo.kwic.entity.CorpusItem;

import java.util.List;

public interface CorpusItemService {

   List<CorpusItem> getBatch(String batchId);

   void saveBatch(String batchId, List<CorpusItem> items);

}
