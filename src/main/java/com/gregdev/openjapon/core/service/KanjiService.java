package com.gregdev.openjapon.core.service;

import com.gregdev.openjapon.core.entity.Kanji;
import com.gregdev.openjapon.core.repository.KanjiRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KanjiService {

    @Autowired
    private KanjiRepositoryInterface kanjiRepository;

    @Autowired
    private MeaningsFrService meaningsFrService;

    @Autowired
    private ReadingsKunService readingsKunService;

    @Autowired
    private ReadingsOnService readingsOnService;


    public Iterable<Kanji> getKanjiList() {
        Iterable<Kanji> kanjis = kanjiRepository.findAll();
        for(Kanji kanji: kanjis){
            kanji.setMeaningsFrList(meaningsFrService.getMeanFrByIdKanji(kanji.getId()));
            kanji.setReadingsOnList(readingsOnService.getReadOnByIdKanji(kanji.getId()));
            kanji.setReadingsKunList(readingsKunService.getReadKunByIdKanji(kanji.getId()));
        }
        return kanjis;
    }

    public List<Kanji> getKanjiBy(Byte stroke, Character symbol) {

        Iterable<Kanji> kanjisDb = kanjiRepository.findAll();
        List<Kanji> kanjis = new ArrayList<>();
        for (Kanji kanji: kanjisDb){
            if(stroke != null && symbol != null){
                if(kanji.getSymbol().equals(symbol) && kanji.getStrokes().equals(stroke) ){
                    kanjis.add(kanji);
                }
            }
            else if(stroke == null && symbol != null){
                if(kanji.getSymbol().equals(symbol)){
                    kanjis.add(kanji);
                }
            }
            else if(stroke != null){
                if(kanji.getStrokes().equals(stroke)){
                    kanjis.add(kanji);
                }
            }
        }
        return kanjis;
    }

    public Kanji getKanjiById(Long id){
        Optional<Kanji> optionalKanji = kanjiRepository.findById(id);
        if(optionalKanji.isEmpty()){
            throw new NoSuchElementException();
        }
        return optionalKanji.get();
    }

    public Long getCountList() {
        return kanjiRepository.count();
    }

    public KanjiRepositoryInterface getKanjiRepository() {
        return kanjiRepository;
    }

    public void setKanjiRepository(KanjiRepositoryInterface kanjiRepository) {
        this.kanjiRepository = kanjiRepository;
    }

    public MeaningsFrService getMeaningsFrService() {
        return meaningsFrService;
    }

    public void setMeaningsFrService(MeaningsFrService meaningsFrService) {
        this.meaningsFrService = meaningsFrService;
    }

    public ReadingsKunService getReadingsKunService() {
        return readingsKunService;
    }

    public void setReadingsKunService(ReadingsKunService readingsKunService) {
        this.readingsKunService = readingsKunService;
    }

    public ReadingsOnService getReadingsOnService() {
        return readingsOnService;
    }

    public void setReadingsOnService(ReadingsOnService readingsOnService) {
        this.readingsOnService = readingsOnService;
    }
}
