package com.gregdev.openjapon.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class ReadingsOn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Column(nullable = false, length = 50)
    private String readingOn;
    @Column(nullable = false)
    @JsonIgnore
    private Long idKanji;

    public ReadingsOn(Long id, String readingOn, Long idKanji) {
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

    public Long getIdKanji() {
        return idKanji;
    }

    public void setIdKanji(Long idKanji) {
        this.idKanji = idKanji;
    }
}
