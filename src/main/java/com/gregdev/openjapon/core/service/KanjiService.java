package com.gregdev.openjapon.core.service;

import com.gregdev.openjapon.core.entity.Kanji;
import com.gregdev.openjapon.core.repository.KanjiRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
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


    /***********************************************************/
    /**                   FONCTIONS READ                      **/
    /***********************************************************/

    /**
     * getKanjiBy(Byte stroke, Pageable page)
     * Recherche d'une liste de kanji en fonction de paramètres
     *
     * @param strokes Byte
     * @param page   Pageable
     * @return Map<String, Object>
     */
    public Map<String, Object> getKanjiBy(Byte strokes, Pageable page) {

        Page<Kanji> pageKanjis;
        if (strokes == null) {
            pageKanjis = kanjiRepository.findAll(page);
        } else {
            pageKanjis = kanjiRepository.findByStrokes(strokes, page);
        }
        return this.getKanjiMap(pageKanjis);
    }

    /**
     * getKanjiById(Long id)
     *
     * @param id Long
     * @return Optional<Kanji>
     */
    public Optional<Kanji> getKanjiById(Long id) {
        Optional<Kanji> optionalKanji = kanjiRepository.findById(id);
        if (optionalKanji.isEmpty()) {
            throw new NoSuchElementException();
        }
        return optionalKanji;
    }

    /**
     * add(Kanji kanji)
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

    /***********************************************************/
    /**                 FONCTIONS CREATE                      **/
    /***********************************************************/

    /**
     * addAll(List<Kanji> kanjis)
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
     * getKanjiMap(Page<Kanji> page)
     * Formatage du contenu de la réponse
     *
     * @param page Page<Kanji>
     * @return Map<String, Object>
     */
    private Map<String, Object> getKanjiMap(Page<Kanji> page) {
        Map<String, Object> kanjis = new HashMap<>();
        kanjis.put("contentData", page.getContent());
        kanjis.put("currentPage", page.getNumber() + 1);
        kanjis.put("totalItems", page.getTotalElements());
        kanjis.put("totalPages", page.getTotalPages());
        kanjis.put("hasNext", page.hasNext());
        kanjis.put("hasPrevious", page.hasPrevious());
        kanjis.put("numberOfElements", page.getNumberOfElements());
        return kanjis;
    }

    /***********************************************************/
    /**                 GETTERS AND SETTERS                   **/
    /***********************************************************/

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
