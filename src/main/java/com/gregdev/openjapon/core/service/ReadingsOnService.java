package com.gregdev.openjapon.core.service;

import com.gregdev.openjapon.core.dto.ReadingsOnDto;
import com.gregdev.openjapon.core.entity.Kanji;
import com.gregdev.openjapon.core.entity.ReadingsOn;
import com.gregdev.openjapon.core.repository.ReadingsOnRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReadingsOnService {

    @Autowired
    private ReadingsOnRepositoryInterface readingsOnRepository;

    /**
     * Sauvegarde en BD les lectures On du kanji
     *
     * @param readingsOnListDto List<ReadingsOn>
     * @param idKanji        Kanji
     * @return List<ReadingsOn>
     */
    public List<ReadingsOn> saveList(List<ReadingsOnDto> readingsOnListDto, Kanji idKanji) {
        List<ReadingsOn> readingsOnsWithId = new ArrayList<>();
        for (ReadingsOnDto readingsOnDto : readingsOnListDto) {
            ReadingsOn readingsOn = new ReadingsOn();
            readingsOn.setKanji(idKanji);
            readingsOn.setReadingOn(readingsOnDto.getReadingOn());
            readingsOnsWithId.add(readingsOnRepository.save(readingsOn));
        }
        return readingsOnsWithId;
    }
}
