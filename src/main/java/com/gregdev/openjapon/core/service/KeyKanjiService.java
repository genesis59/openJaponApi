package com.gregdev.openjapon.core.service;


import com.gregdev.openjapon.core.dto.KeyKanjiDto;
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

    public KeyKanji getKeyKanjiById(Long id) {
        Optional<KeyKanji> optKanji = keyKanjiRepository.findById(id);
        System.out.println("ici");
        if (optKanji.isEmpty()) {
            return null;
        }
        return optKanji.get();
    }

    public List<KeyKanji> getKeyKanjiByListId(List<KeyKanjiDto> keyKanjiDtoList) {
        List<KeyKanji> keyKanjiList = new ArrayList<>();
        for (KeyKanjiDto keyKanjiDto : keyKanjiDtoList) {
            keyKanjiList.add(this.getKeyKanjiById(keyKanjiDto.getId()));
        }
        return keyKanjiList;
    }

}
