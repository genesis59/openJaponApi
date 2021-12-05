package com.gregdev.openjapon.core.api;

import com.gregdev.openjapon.core.entity.Kanji;
import com.gregdev.openjapon.core.service.KanjiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/kanji")
public class KanjiResource {

    @Autowired
    private KanjiService kanjiService;

    @GetMapping
    public Iterable<Kanji> list(@RequestParam(required = false) Byte stroke, @RequestParam(required = false) Character symbol) {
        if (stroke != null || symbol != null) {
            return kanjiService.getKanjiBy(stroke, symbol);
        }
        return kanjiService.getKanjiList();
    }

    /**
     * Routes GET
     */

    @GetMapping("/{id}")
    public Optional<Kanji> getKanjiById(@PathVariable("id") Long id) {
        return kanjiService.getKanjiById(id);
    }

    @GetMapping("/count")
    public Long getKanjiById() {
        return kanjiService.getCountList();
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
