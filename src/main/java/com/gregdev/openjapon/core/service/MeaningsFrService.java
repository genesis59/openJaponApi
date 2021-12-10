package com.gregdev.openjapon.core.service;

import com.gregdev.openjapon.core.dto.MeaningsFrDto;
import com.gregdev.openjapon.core.entity.Kanji;
import com.gregdev.openjapon.core.entity.MeaningsFr;
import com.gregdev.openjapon.core.repository.MeaningsFrRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeaningsFrService {

    @Autowired
    private MeaningsFrRepositoryInterface meaningsFrRepository;


    /**
     * Retourne les id des kanji ayant cette signification
     *
     * @param meaningFr String
     * @return List<Long>
     */
    public List<Long> getListIdKanjiByMeaningFr(String meaningFr) {
        Iterable<MeaningsFr> means = meaningsFrRepository.findByMeaningFr(meaningFr);
        List<Long> idKanjis = new ArrayList<>();
        for (MeaningsFr mean : means) {
            idKanjis.add(mean.getIdKanji());
        }
        return idKanjis;
    }

    /**
     * Sauvegarde en BD les significations en français du kanji
     *
     * @param meaningsFrListDto List<MeaningsFrDto>
     * @param idKanji        Kanji
     * @return List<MeaningsFr>
     */
    public List<MeaningsFr> saveList(List<MeaningsFrDto> meaningsFrListDto, Kanji idKanji) {
        List<MeaningsFr> meaningsFrListWithId = new ArrayList<>();
        for (MeaningsFrDto meaningsFrDto : meaningsFrListDto) {
            MeaningsFr meaningsFr = new MeaningsFr();
            meaningsFr.setMeaningFr(meaningsFrDto.getMeaningFr());
            meaningsFr.setKanji(idKanji);
            meaningsFrListWithId.add(meaningsFrRepository.save(meaningsFr));
        }
        return meaningsFrListWithId;
    }

}
