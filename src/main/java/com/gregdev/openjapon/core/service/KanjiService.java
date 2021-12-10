package com.gregdev.openjapon.core.service;

import com.gregdev.openjapon.core.dto.KanjiDto;
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

    @Autowired
    private KeyKanjiService keyKanjiService;


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
    public Kanji getKanjiById(Long id) {
        Optional<Kanji> optionalKanji = kanjiRepository.findById(id);
        if (optionalKanji.isEmpty()) {
            return null;
        }
        return optionalKanji.get();
    }


    /***********************************************************/
    /**                 FONCTIONS CREATE                      **/
    /***********************************************************/

    /**
     * Sauvegarde d'un Kanji et de ses informations
     *
     * @param kanjiDto Kanji
     * @return Kanji
     */
    public Kanji add(KanjiDto kanjiDto) {
        try {

            Kanji newKanji = new Kanji();
            newKanji.setStrokes(kanjiDto.getStrokes());
            newKanji.setSymbol(kanjiDto.getSymbol());
            Kanji kanjiWithId = kanjiRepository.save(newKanji);

            kanjiWithId.setKeyKanji(keyKanjiService.getKeyKanjiByListId(kanjiDto.getKeyKanjiList()));
            kanjiWithId.setMeaningsFrList(meaningsFrService.saveList(kanjiDto.getMeaningsFrList(), kanjiWithId));
            kanjiWithId.setReadingsKunList(readingsKunService.saveList(kanjiDto.getReadingsKunList(), kanjiWithId));
            kanjiWithId.setReadingsOnList(readingsOnService.saveList(kanjiDto.getReadingsOnList(), kanjiWithId));
            return kanjiWithId;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;

    }

    /**
     * Sauvegarde d'une liste de Kanji et de leurs informations
     *
     * @param kanjisDto List<KanjiDto>
     * @return List<Kanji>
     */
    public List<Kanji> addAll(List<KanjiDto> kanjisDto) {

        List<Kanji> newKanjis = new ArrayList<>();
        for (KanjiDto kanjiDto : kanjisDto) {
            Kanji newKanji = this.add(kanjiDto);
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
