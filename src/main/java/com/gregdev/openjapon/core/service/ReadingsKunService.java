package com.gregdev.openjapon.core.service;

import com.gregdev.openjapon.core.dto.ReadingsKunDto;
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
     * Récupère les ids des kanji ayant cette lecture
     *
     * @param readingKun String
     * @return List<Long>
     */
    public List<Long> getListIdKanjiByReadingKun(String readingKun) {
        Iterable<ReadingsKun> readsKun = readingsKunRepository.findByReadingKun(readingKun);

        List<Long> idKanjis = new ArrayList<>();
        for (ReadingsKun readingsKun : readsKun) {
            idKanjis.add(readingsKun.getIdKanji());
        }
        if (idKanjis.isEmpty()) {
            return null;
        }
        return idKanjis;
    }

    /**
     * Sauvegarde en BD les lectures Kun du kanji
     *
     * @param readingsKunListDto List<ReadingsKun>
     * @param idKanji            Kanji
     * @return List<ReadingsKun>
     */
    public List<ReadingsKun> saveList(List<ReadingsKunDto> readingsKunListDto, Kanji idKanji) {
        List<ReadingsKun> readingsKunsWithId = new ArrayList<>();
        for (ReadingsKunDto readingsKunDto : readingsKunListDto) {
            ReadingsKun readingsKun = new ReadingsKun();
            readingsKun.setKanji(idKanji);
            readingsKun.setReadingKun(readingsKunDto.getReadingKun());
            readingsKunsWithId.add(readingsKunRepository.save(readingsKun));
        }
        return readingsKunsWithId;
    }
}
