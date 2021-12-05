package com.gregdev.openjapon.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class ReadingsKun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String readingKun;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_KANJI")
    @JsonIgnore
    private Kanji idKanji;

    public ReadingsKun(Long id, String readingKun, Kanji idKanji) {
        this.id = id;
        this.readingKun = readingKun;
        this.idKanji = idKanji;
    }

    public ReadingsKun() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReadingKun() {
        return readingKun;
    }

    public void setReadingKun(String readingKun) {
        this.readingKun = readingKun;
    }

    public Kanji getIdKanji() {
        return idKanji;
    }

    public void setIdKanji(Kanji idKanji) {
        this.idKanji = idKanji;
    }
}
