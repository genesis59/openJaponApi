package com.gregdev.openjapon.core.service;

import com.gregdev.openjapon.core.api.KanjiResource;
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
     * @param typeSearch String
     * @param search     String
     * @param strokes    Byte
     * @param page       Pageable
     * @return Map<String, Object>
     */
    public Map<String, Object> getKanjiBy(String typeSearch, String search, Byte strokes, Pageable page) {
        Page<Kanji> pageKanjis = null;
        if (typeSearch != null) {
            List<Long> listIdKanji = this.getListIdByTypeSearch(typeSearch, search);
            if (listIdKanji != null) {
                if (strokes != null) {
                    pageKanjis = kanjiRepository.findByIdInAndStrokes(listIdKanji, strokes, page);
                } else {
                    pageKanjis = kanjiRepository.findByIdIn(listIdKanji, page);
                }
            }
        } else {
            if (strokes != null) {
                pageKanjis = kanjiRepository.findByStrokes(strokes, page);
            } else {
                pageKanjis = kanjiRepository.findAll(page);
            }
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
        if (page == null || page.getContent().isEmpty()) {
            return null;
        } else {
            kanjis.put("contentData", page.getContent());
            kanjis.put("currentPage", page.getNumber() + 1);
            kanjis.put("totalItems", page.getTotalElements());
            kanjis.put("totalPages", page.getTotalPages());
            kanjis.put("hasNext", page.hasNext());
            kanjis.put("hasPrevious", page.hasPrevious());
            kanjis.put("numberOfElements", page.getNumberOfElements());
        }
        return kanjis;
    }

    /**
     * Détermine le service à utiliser en fonction du type de recherche
     *
     * @param typeSearch String
     * @param search     String
     * @return List<Long>
     */
    private List<Long> getListIdByTypeSearch(String typeSearch, String search) {
        switch (typeSearch) {
            case KanjiResource.TYPE_SEARCH_MEAN_FR:
                return meaningsFrService.getListIdKanjiByMeaningFr(search);
            case KanjiResource.TYPE_SEARCH_READ_KUN:
                return readingsKunService.getListIdKanjiByReadingKun(search);
            case KanjiResource.TYPE_SEARCH_READ_ON:
                return readingsOnService.getListIdKanjiByReadingOn(search);
            case KanjiResource.TYPE_SEARCH_ID_KEY_KANJI:
                return keyKanjiService.getIdKanjiListById(Long.parseLong(search));
            default:
                return null;
        }
    }
}
