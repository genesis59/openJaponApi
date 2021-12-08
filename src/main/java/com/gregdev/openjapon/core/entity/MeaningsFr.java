package com.gregdev.openjapon.core.entity;

import javax.persistence.*;

@Entity
public class MeaningsFr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String meaningFr;

    /**
     * Le getter retourne l'id uniquement
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_KANJI")
    private Kanji kanji;

    public MeaningsFr(Long id, String meaningFr, Kanji idKanji) {
        this.id = id;
        this.meaningFr = meaningFr;
        this.kanji = idKanji;
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
        return kanji.getId();
    }

    public void setKanji(Kanji idKanji) {
        this.kanji = idKanji;
    }
}
