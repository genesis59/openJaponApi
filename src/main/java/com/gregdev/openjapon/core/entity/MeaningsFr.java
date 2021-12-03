package com.gregdev.openjapon.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class MeaningsFr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Column(nullable = false,length = 50)
    private String meaningFr;
    @Column(nullable = false)
    @JsonIgnore
    private Long idKanji;

    public MeaningsFr(Long id, String meaningFr, Long idKanji) {
        this.id = id;
        this.meaningFr = meaningFr;
        this.idKanji = idKanji;
    }

    public MeaningsFr() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeaningFr() {
        return meaningFr;
    }

    public void setMeaningFr(String meaningFr) {
        this.meaningFr = meaningFr;
    }

    public Long getIdKanji() {
        return idKanji;
    }

    public void setIdKanji(Long idKanji) {
        this.idKanji = idKanji;
    }
}
