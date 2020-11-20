package com.patterndemo.kwic.dto;

import com.patterndemo.kwic.entity.CorpusItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorpusItemList {

    private List<CorpusItem> items;

}
