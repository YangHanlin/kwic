package com.patterndemo.kwic.dto;

import com.patterndemo.kwic.entity.IndexedResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndexedResultList {

    private List<IndexedResult> results;

}
