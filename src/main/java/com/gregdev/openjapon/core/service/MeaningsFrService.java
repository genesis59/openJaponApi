package com.gregdev.openjapon.core.service;

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

    public List<MeaningsFr> getMeanFrByIdKanji(Long id){
        Iterable<MeaningsFr> meanList = meaningsFrRepositoryInterface.findAll();
        List<MeaningsFr> meanListForKanji= new ArrayList<>();
        for (MeaningsFr mean: meanList){
            if(mean.getIdKanji().equals(id)){
                meanListForKanji.add(mean);
            }
        }
        return meanListForKanji;
    }

    public MeaningsFrRepositoryInterface getMeaningsFrRepositoryInterface() {
        return meaningsFrRepositoryInterface;
    }

    public void setMeaningsFrRepositoryInterface(MeaningsFrRepositoryInterface meaningsFrRepositoryInterface) {
        this.meaningsFrRepositoryInterface = meaningsFrRepositoryInterface;
    }
}
