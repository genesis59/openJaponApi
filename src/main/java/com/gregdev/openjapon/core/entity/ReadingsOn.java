package com.gregdev.openjapon.core.entity;

import javax.persistence.*;

@Entity
public class ReadingsOn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String readingOn;

    /**
     * Le getter retourne l'id uniquement
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_KANJI")
    private Kanji kanji;

    public ReadingsOn(Long id, String readingOn, Kanji idKanji) {
        this.id = id;
        this.readingOn = readingOn;
        this.kanji = idKanji;
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
        return kanji.getId();
    }

    public void setKanji(Kanji idKanji) {
        this.kanji = idKanji;
    }
}
