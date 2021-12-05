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

    /**
     * Récupère la liste complète des kanji en BD et leurs informations
     *
     * @return Iterable<Kanji>
     */
    public Iterable<Kanji> getKanjiList() {
        return kanjiRepository.findAll();
    }

    /**
     * Sauvegarde d'un Kanji et de ses informations
     *
     * @param kanji Kanji
     * @return Kanji
     */
    public Kanji add(Kanji kanji) {
        /* Création et ajout à la base de données d'une nouvelle entité Kanji à partir
         * de l'argument kanji
         */
        Kanji newKanji = new Kanji();
        newKanji.setStrokes(kanji.getStrokes());
        newKanji.setSymbol(kanji.getSymbol());
        newKanji.setKeyKanji(kanji.getKeyKanji());
        Kanji kanjiWithId = kanjiRepository.save(newKanji);

        /* Ajout du nouvel identifiant du kanji aux autres entités */
        kanjiWithId.setMeaningsFrList(meaningsFrService.saveList(kanji.getMeaningsFrList(), kanjiWithId));
        kanjiWithId.setReadingsKunList(readingsKunService.saveList(kanji.getReadingsKunList(), kanjiWithId));
        kanjiWithId.setReadingsOnList(readingsOnService.saveList(kanji.getReadingsOnList(), kanjiWithId));
        return kanjiWithId;
    }

    /**
     * Sauvegarde d'une liste de Kanji et de leurs informations
     *
     * @param kanjis List<Kanji>
     * @return List<Kanji>
     */
    public List<Kanji> addAll(List<Kanji> kanjis) {

        List<Kanji> newKanjis = new ArrayList<>();
        for (Kanji kanji : kanjis) {
            Kanji newKanji = this.add(kanji);
            newKanjis.add(newKanji);
        }
        return newKanjis;
    }

    /**
     * Recherche d'une liste de kanji en fonction de paramètres
     *
     * @param stroke Character
     * @param symbol Byte
     * @return List<Kanji>
     */
    public List<Kanji> getKanjiBy(Byte stroke, Character symbol) {

        Iterable<Kanji> kanjisDb = kanjiRepository.findAll();
        List<Kanji> kanjis = new ArrayList<>();
        for (Kanji kanji : kanjisDb) {
            if (stroke != null && symbol != null) {
                if (kanji.getSymbol().equals(symbol) && kanji.getStrokes().equals(stroke)) {
                    kanjis.add(kanji);
                }
            } else if (stroke == null && symbol != null) {
                if (kanji.getSymbol().equals(symbol)) {
                    kanjis.add(kanji);
                }
            } else if (stroke != null) {
                if (kanji.getStrokes().equals(stroke)) {
                    kanjis.add(kanji);
                }
            }
        }
        return kanjis;
    }

    public Optional<Kanji> getKanjiById(Long id) {
        Optional<Kanji> optionalKanji = kanjiRepository.findById(id);
        if (optionalKanji.isEmpty()) {
            throw new NoSuchElementException();
        }
        return optionalKanji;
    }

    /**
     * Nombre de kanji en BD
     *
     * @return Long
     */
    public Long getCountList() {
        return kanjiRepository.count();
    }


    /**
     * getters and setters
     **/

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
