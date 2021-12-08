package com.gregdev.openjapon.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class ReadingsOn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String readingOn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_KANJI")
    @JsonIgnore
    private Kanji idKanji;

    public ReadingsOn(Long id, String readingOn, Kanji idKanji) {
        this.id = id;
        this.readingOn = readingOn;
        this.idKanji = idKanji;
    }

    public ReadingsOn() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReadingOn() {
        return readingOn;
    }

    public void setReadingOn(String readingOn) {
        this.readingOn = readingOn;
    }

    public Kanji getIdKanji() {
        return idKanji;
    }

    public void setIdKanji(Kanji idKanji) {
        this.idKanji = idKanji;
    }
}
