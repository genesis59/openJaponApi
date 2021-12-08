package com.gregdev.openjapon.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class MeaningsFr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String meaningFr;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_KANJI")
    @JsonIgnore
    private Kanji idKanji;

    public MeaningsFr(Long id, String meaningFr, Kanji idKanji) {
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

    public Kanji getIdKanji() {
        return idKanji;
    }

    public void setIdKanji(Kanji idKanji) {
        this.idKanji = idKanji;
    }
}
