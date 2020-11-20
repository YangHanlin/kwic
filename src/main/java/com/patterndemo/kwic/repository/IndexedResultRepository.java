package com.patterndemo.kwic.repository;

import com.patterndemo.kwic.entity.IndexedResult;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IndexedResultRepository extends CrudRepository<IndexedResult, Long> {

    List<IndexedResult> findByBatchId(String batchId);

}
