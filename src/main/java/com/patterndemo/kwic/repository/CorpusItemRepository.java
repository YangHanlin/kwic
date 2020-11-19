package com.patterndemo.kwic.repository;

import com.patterndemo.kwic.entity.CorpusItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

public interface CorpusItemRepository extends CrudRepository<CorpusItem, Long> {

    List<CorpusItem> findByBatchId(String batchId);

}
