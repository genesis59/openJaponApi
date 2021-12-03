package com.gregdev.openjapon.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Kanji {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Column(nullable = false,length = 1)
    private Character symbol;
    @Column(nullable = false)
    private Byte strokes;
    @ManyToMany
    @JoinTable(
            name = "KANJI_KEY_KANJI",
            joinColumns = {@JoinColumn(name = "ID_KANJI")},
            inverseJoinColumns = {@JoinColumn(name = "ID_KEY_KANJI")}
    )
    private List<KeyKanji> keyKanji = new ArrayList<>();
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<MeaningsFr> meaningsFrList = new ArrayList<>();
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ReadingsKun> readingsKunList = new ArrayList<>();
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ReadingsOn> readingsOnList = new ArrayList<>();


    public Kanji(Long id, Character symbol, Byte strokes) {
        this.id = id;
        this.symbol = symbol;
        this.strokes = strokes;
    }

    public Kanji() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Character getSymbol() {
        return symbol;
    }

    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }

    public Byte getStrokes() {
        return strokes;
    }

    public void setStrokes(Byte strokes) {
        this.strokes = strokes;
    }

    public List<KeyKanji> getKeyKanji() {
        return keyKanji;
    }

    public void setKeyKanji(List<KeyKanji> keyKanji) {
        this.keyKanji = keyKanji;
    }

    public List<MeaningsFr> getMeaningsFrList() {
        return meaningsFrList;
    }

    public void setMeaningsFrList(List<MeaningsFr> meaningsFrList) {
        this.meaningsFrList = meaningsFrList;
    }

    public List<ReadingsKun> getReadingsKunList() {
        return readingsKunList;
    }

    public void setReadingsKunList(List<ReadingsKun> readingsKunList) {
        this.readingsKunList = readingsKunList;
    }

    public List<ReadingsOn> getReadingsOnList() {
        return readingsOnList;
    }

    public void setReadingsOnList(List<ReadingsOn> readingsOnList) {
        this.readingsOnList = readingsOnList;
    }
}
