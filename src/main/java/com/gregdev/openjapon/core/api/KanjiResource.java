package com.gregdev.openjapon.core.api;

import com.gregdev.openjapon.core.entity.Kanji;
import com.gregdev.openjapon.core.service.KanjiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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


    /**
     * Filtre et pagine une liste de kanjis
     *
     * @param strokes Byte
     * @param page   String
     * @return ResponseEntity<Map < String, Object>>
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> list(
            @RequestParam(required = false) Byte strokes,
            @RequestParam(defaultValue = "5") String size,
            @RequestParam(defaultValue = "1") String page)
    {
        try {
            Map<String, Object> response = kanjiService.getKanjiBy(strokes, PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size)));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public Optional<Kanji> getKanjiById(@PathVariable("id") Long id) {
        return kanjiService.getKanjiById(id);
    }

    /**
     * Routes POST
     */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kanji save(@RequestBody Kanji kanji) {
        return kanjiService.add(kanji);
    }

    @PostMapping("/all")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Kanji> saveAll(@RequestBody List<Kanji> kanjis) {
        return kanjiService.addAll(kanjis);
    }

    /**
     * getters and setters
     */

    public KanjiService getKanjiService() {
        return kanjiService;
    }

    public void setKanjiService(KanjiService kanjiService) {
        this.kanjiService = kanjiService;
    }

}
