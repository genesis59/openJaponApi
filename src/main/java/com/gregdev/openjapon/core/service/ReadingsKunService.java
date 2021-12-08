package com.gregdev.openjapon.core.service;

import com.gregdev.openjapon.core.entity.Kanji;
import com.gregdev.openjapon.core.entity.ReadingsKun;
import com.gregdev.openjapon.core.repository.ReadingsKunRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReadingsKunService {

    @Autowired
    private ReadingsKunRepositoryInterface readingsKunRepository;

    /**
     * Sauvegarde en BD les lectures Kun du kanji
     *
     * @param readingsKunList List<ReadingsKun>
     * @param idKanji         Kanji
     * @return List<ReadingsKun>
     */
    public List<ReadingsKun> saveList(List<ReadingsKun> readingsKunList, Kanji idKanji) {
        List<ReadingsKun> readingsKunsWithId = new ArrayList<>();
        for (ReadingsKun readingsKun : readingsKunList) {

            readingsKun.setKanji(idKanji);
            ReadingsKun newReadingKun = readingsKunRepository.save(readingsKun);
            readingsKunsWithId.add(newReadingKun);
        }
        return readingsKunsWithId;
    }
}
