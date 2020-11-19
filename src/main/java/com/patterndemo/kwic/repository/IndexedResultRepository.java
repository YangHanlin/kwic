package com.patterndemo.kwic.repository;

import com.patterndemo.kwic.entity.IndexedResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

public interface IndexedResultRepository extends CrudRepository<IndexedResult, Long> {

    List<IndexedResult> findByBatchId(String batchId);

}
