package com.gregdev.openjapon.core.service;

import com.gregdev.openjapon.core.entity.Kanji;
import com.gregdev.openjapon.core.repository.KanjiRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * Recherche d'une liste de kanji en fonction des paramètres renseignés
     *
     * @param strokes   Byte
     * @param page      Pageable
     * @param meaningFr String
     * @return Map<String, Object>
     */
    public Map<String, Object> getKanjiBy(String meaningFr, Byte strokes, Pageable page) {

        Page<Kanji> pageKanjis;
        if (strokes == null && meaningFr == null) {
            pageKanjis = kanjiRepository.findAll(page);
        } else if (strokes != null && meaningFr != null) {
            pageKanjis = kanjiRepository.findByIdInAndStrokes(meaningsFrService.getListIdKanjiByMeaningFr(meaningFr), strokes, page);
        } else if (strokes != null) {
            pageKanjis = kanjiRepository.findByStrokes(strokes, page);
        } else {
            pageKanjis = kanjiRepository.findByIdIn(meaningsFrService.getListIdKanjiByMeaningFr(meaningFr), page);
        }
        return this.getKanjiMap(pageKanjis);
    }

    /**
     * getKanjiById
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


    /***********************************************************/
    /**                 FONCTIONS CREATE                      **/
    /***********************************************************/

    /**
     * Sauvegarde d'un Kanji et de ses informations
     *
     * @param kanji Kanji
     * @return Kanji
     */
    public Kanji add(Kanji kanji) {

        Kanji newKanji = new Kanji();
        newKanji.setStrokes(kanji.getStrokes());
        newKanji.setSymbol(kanji.getSymbol());
        newKanji.setKeyKanji(kanji.getKeyKanji());
        Kanji kanjiWithId = kanjiRepository.save(newKanji);

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

    /***********************************************************/
    /**                 AUTRES FONCTIONS                      **/
    /***********************************************************/

    /**
     * Construction du contenu de la réponse JSON
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
}
