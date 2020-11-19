package com.patterndemo.kwic.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "result")
public class IndexedResult {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "batch_id")
    private String batchId;

    @Column(name = "content")
    private String content;

}
