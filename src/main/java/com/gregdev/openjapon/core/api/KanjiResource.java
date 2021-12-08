package com.gregdev.openjapon.core.api;

import com.gregdev.openjapon.core.entity.Kanji;
import com.gregdev.openjapon.core.service.KanjiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/kanji")
public class KanjiResource {

    @Autowired
    private KanjiService kanjiService;

    /***********************************************************/
    /**                      ROUTES GET                       **/
    /***********************************************************/

    /**
     * Filtre et pagine une liste de kanjis
     *
     * @param strokes   Byte
     * @param meaningFr String
     * @param size      String
     * @param page      String
     * @return ResponseEntity<Map < String, Object>>
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> list(
            @RequestParam(required = false) Byte strokes,
            @RequestParam(required = false) String meaningFr,
            @RequestParam(defaultValue = "5") String size,
            @RequestParam(defaultValue = "1") String page) {
        try {
            Map<String, Object> response = kanjiService.getKanjiBy(meaningFr, strokes, PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size)));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * getKanjiById
     *
     * @param id Long
     * @return ResponseEntity<Optional < Kanji>>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Kanji>> getKanjiById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(kanjiService.getKanjiById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /***********************************************************/
    /**                     ROUTES POST                       **/
    /***********************************************************/

    /**
     * Enregistre un kanji en base de données
     *
     * @param kanji Kanji
     * @return ResponseEntity<Kanji>
     */
    @PostMapping
    public ResponseEntity<Kanji> save(@RequestBody Kanji kanji) {
        try {
            return new ResponseEntity<>(kanjiService.add(kanji), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Enregistre une liste de kanji en base de données
     *
     * @param kanjis List<Kanji>
     * @return ResponseEntity<List < Kanji>>
     */
    @PostMapping("/all")
    public ResponseEntity<List<Kanji>> saveAll(@RequestBody List<Kanji> kanjis) {
        try {
            return new ResponseEntity<>(kanjiService.addAll(kanjis), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
