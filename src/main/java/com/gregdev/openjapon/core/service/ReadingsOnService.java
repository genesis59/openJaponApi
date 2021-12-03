package com.gregdev.openjapon.core.service;

import com.gregdev.openjapon.core.entity.ReadingsOn;
import com.gregdev.openjapon.core.repository.ReadingsOnRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReadingsOnService {

    @Autowired
    private ReadingsOnRepositoryInterface readingsOnRepositoryInterface;

    public List<ReadingsOn> getReadOnByIdKanji(Long id){
        Iterable<ReadingsOn> readOnList = readingsOnRepositoryInterface.findAll();
        List<ReadingsOn> readOnListForKanji= new ArrayList<>();
        for (ReadingsOn readOn: readOnList){
            if(readOn.getIdKanji().equals(id)){
                readOnListForKanji.add(readOn);
            }
        }
        return readOnListForKanji;
    }

    public ReadingsOnRepositoryInterface getReadingsOnRepositoryInterface() {
        return readingsOnRepositoryInterface;
    }

    public void setReadingsOnRepositoryInterface(ReadingsOnRepositoryInterface readingsOnRepositoryInterface) {
        this.readingsOnRepositoryInterface = readingsOnRepositoryInterface;
    }
}
