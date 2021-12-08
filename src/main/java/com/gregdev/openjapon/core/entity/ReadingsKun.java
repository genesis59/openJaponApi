package com.gregdev.openjapon.core.entity;

import javax.persistence.*;

@Entity
public class ReadingsKun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String readingKun;

    /**
     * Le getter retourne l'id uniquement
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_KANJI")
    private Kanji kanji;

    public ReadingsKun(Long id, String readingKun, Kanji idKanji) {
        this.id = id;
        this.readingKun = readingKun;
        this.kanji = idKanji;
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

    public Long getIdKanji() {
        return kanji.getId();
    }

    public void setKanji(Kanji idKanji) {
        this.kanji = idKanji;
    }
}
