package com.patterndemo.kwic.util;

import com.patterndemo.kwic.core.output.Output;
import com.patterndemo.kwic.entity.IndexedResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class IndexedResultOutput extends Output<String> {

    @Getter
    @Setter
    private List<IndexedResult> results = new ArrayList<>();

    @Override
    public void putData(String data) {
        IndexedResult result = new IndexedResult();
        result.setContent(data);
        results.add(result);
    }
}
