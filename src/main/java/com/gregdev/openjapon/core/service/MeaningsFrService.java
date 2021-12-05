package com.gregdev.openjapon.core.service;

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
    private MeaningsFrRepositoryInterface meaningsFrRepositoryInterface;

    /**
     * Sauvegarde en BD les significations en français du kanji
     *
     * @param meaningsFrList List<MeaningsFr>
     * @param idKanji        Kanji
     * @return List<MeaningsFr>
     */
    public List<MeaningsFr> saveList(List<MeaningsFr> meaningsFrList, Kanji idKanji) {
        List<MeaningsFr> meaningsFrListWithId = new ArrayList<>();
        for (MeaningsFr meaningsFr : meaningsFrList) {

            meaningsFr.setIdKanji(idKanji);
            MeaningsFr newMeaningFr = meaningsFrRepositoryInterface.save(meaningsFr);
            meaningsFrListWithId.add(newMeaningFr);
        }
        return meaningsFrListWithId;
    }

    /**
     * getters and setters
     */

    public MeaningsFrRepositoryInterface getMeaningsFrRepositoryInterface() {
        return meaningsFrRepositoryInterface;
    }

    public void setMeaningsFrRepositoryInterface(MeaningsFrRepositoryInterface meaningsFrRepositoryInterface) {
        this.meaningsFrRepositoryInterface = meaningsFrRepositoryInterface;
    }
}
