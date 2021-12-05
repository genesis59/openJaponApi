package com.gregdev.openjapon.core.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class KeyKanji {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 1)
    private Character symbolKey;
    @Column(nullable = false, length = 30)
    private String nameKey;
    @Column(nullable = false)
    private Short keyNumber;
    @Column(nullable = false)
    private Byte strokes;
    @ManyToMany(mappedBy = "keyKanji")
    private List<Kanji> kanjis = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Character getSymbolKey() {
        return symbolKey;
    }

    public void setSymbolKey(Character symbolKey) {
        this.symbolKey = symbolKey;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public Short getKeyNumber() {
        return keyNumber;
    }

    public void setKeyNumber(Short keyNumber) {
        this.keyNumber = keyNumber;
    }

    public Byte getStrokes() {
        return strokes;
    }

    public void setStrokes(Byte strokes) {
        this.strokes = strokes;
    }

    /*public List<Kanji> getKanjis() {
        return kanjis;
    }

    public void setKanjis(List<Kanji> kanjis) {
        this.kanjis = kanjis;
    }*/
}
