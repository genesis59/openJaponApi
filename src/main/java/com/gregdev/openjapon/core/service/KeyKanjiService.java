package com.gregdev.openjapon.core.service;


import com.gregdev.openjapon.core.dto.KeyKanjiDto;
import com.gregdev.openjapon.core.entity.Kanji;
import com.gregdev.openjapon.core.entity.KeyKanji;
import com.gregdev.openjapon.core.repository.KeyKanjiRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KeyKanjiService {

    @Autowired
    private KeyKanjiRepositoryInterface keyKanjiRepository;

    /**
     * getKeyKanjiById
     *
     * @param id Long
     * @return KeyKanji
     */
    public KeyKanji getKeyKanjiById(Long id) {
        Optional<KeyKanji> optKanji = keyKanjiRepository.findById(id);
        if (optKanji.isEmpty()) {
            return null;
        }
        return optKanji.get();
    }

    /**
     * Récupère les ids des kanji ayant cette clé
     *
     * @param id Long
     * @return List<Long>
     */
    public List<Long> getIdKanjiListById(Long id) {
        Optional<KeyKanji> optKanji = keyKanjiRepository.findById(id);
        if (optKanji.isEmpty()) {
            return null;
        }
        List<Long> idKanjis = new ArrayList<>();
        for (Kanji kanji : optKanji.get().getKanjis()) {
            idKanjis.add(kanji.getId());
        }
        return idKanjis;
    }

    /**
     * Récupère les keyKanji à partir d'une liste de KeyKanjiDto
     *
     * @param keyKanjiDtoList List<KeyKanjiDto>
     * @return List<KeyKanji>
     */
    public List<KeyKanji> getKeyKanjiByListId(List<KeyKanjiDto> keyKanjiDtoList) {
        List<KeyKanji> keyKanjiList = new ArrayList<>();
        for (KeyKanjiDto keyKanjiDto : keyKanjiDtoList) {
            keyKanjiList.add(this.getKeyKanjiById(keyKanjiDto.getId()));
        }
        return keyKanjiList;
    }

}
