package com.gregdev.openjapon.core.api;

import com.gregdev.openjapon.core.entity.Kanji;
import com.gregdev.openjapon.core.service.KanjiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kanji")
public class KanjiResource {

    @Autowired
    private KanjiService kanjiService;

    public KanjiService getKanjiService() {
        return kanjiService;
    }

    public void setKanjiService(KanjiService kanjiService) {
        this.kanjiService = kanjiService;
    }

    @GetMapping
    public Iterable<Kanji> list(@RequestParam(required = false) Byte stroke, @RequestParam(required = false) Character symbol){
        System.out.println(symbol);
        if(stroke != null || symbol != null){
            return kanjiService.getKanjiBy(stroke,symbol);
        }
        return kanjiService.getKanjiList();
    }

    @GetMapping("/{id}")
    public Kanji getKanjiById(@PathVariable("id") Long id){
        return kanjiService.getKanjiById(id);
    }

    @GetMapping("/count")
    public Long getKanjiById(){
        return kanjiService.getCountList();
    }

}
