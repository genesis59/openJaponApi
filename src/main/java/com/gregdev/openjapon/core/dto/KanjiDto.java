package com.gregdev.openjapon.core.dto;

import java.util.ArrayList;
import java.util.List;


public class KanjiDto {

    private Character symbol;

    private Byte strokes;

    private List<KeyKanjiDto> keyKanjiList = new ArrayList<>();

    private List<MeaningsFrDto> meaningsFrList = new ArrayList<>();

    private List<ReadingsKunDto> readingsKunList = new ArrayList<>();

    private List<ReadingsOnDto> readingsOnList = new ArrayList<>();


    public KanjiDto(Character symbol, Byte strokes) {
        this.symbol = symbol;
        this.strokes = strokes;
    }

    public KanjiDto() {
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

    public List<KeyKanjiDto> getKeyKanjiList() {
        return keyKanjiList;
    }

    public void setKeyKanjiList(List<KeyKanjiDto> keyKanjiList) {
        this.keyKanjiList = keyKanjiList;
    }

    public List<MeaningsFrDto> getMeaningsFrList() {
        return meaningsFrList;
    }

    public void setMeaningsFrList(List<MeaningsFrDto> meaningsFrList) {
        this.meaningsFrList = meaningsFrList;
    }

    public List<ReadingsKunDto> getReadingsKunList() {
        return readingsKunList;
    }

    public void setReadingsKunList(List<ReadingsKunDto> readingsKunList) {
        this.readingsKunList = readingsKunList;
    }

    public List<ReadingsOnDto> getReadingsOnList() {
        return readingsOnList;
    }

    public void setReadingsOnList(List<ReadingsOnDto> readingsOnList) {
        this.readingsOnList = readingsOnList;
    }
}
