package com.gregdev.openjapon.core.service;

import com.gregdev.openjapon.core.entity.ReadingsKun;
import com.gregdev.openjapon.core.repository.ReadingsKunRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReadingsKunService {

    @Autowired
    private ReadingsKunRepositoryInterface readingsKunRepositoryInterface;

    public List<ReadingsKun> getReadKunByIdKanji(Long id){
        Iterable<ReadingsKun> readKunList = readingsKunRepositoryInterface.findAll();
        List<ReadingsKun> readKunListForKanji= new ArrayList<>();
        for (ReadingsKun readKun: readKunList){
            if(readKun.getIdKanji().equals(id)){
                readKunListForKanji.add(readKun);
            }
        }
        return readKunListForKanji;
    }

    public ReadingsKunRepositoryInterface getReadingsKunRepositoryInterface() {
        return readingsKunRepositoryInterface;
    }

    public void setReadingsKunRepositoryInterface(ReadingsKunRepositoryInterface readingsKunRepositoryInterface) {
        this.readingsKunRepositoryInterface = readingsKunRepositoryInterface;
    }
}
