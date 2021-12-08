package com.gregdev.openjapon.core.service;

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
    private ReadingsOnRepositoryInterface readingsOnRepositoryInterface;

    /**
     * saveList(List<ReadingsOn> readingsOnList, Kanji idKanji)
     * Sauvegarde en BD les lectures On du kanji
     *
     * @param readingsOnList List<ReadingsOn>
     * @param idKanji        Kanji
     * @return List<ReadingsOn>
     */
    public List<ReadingsOn> saveList(List<ReadingsOn> readingsOnList, Kanji idKanji) {
        List<ReadingsOn> readingsOnsWithId = new ArrayList<>();
        for (ReadingsOn readingsOn : readingsOnList) {

            readingsOn.setIdKanji(idKanji);
            ReadingsOn newReadingOn = readingsOnRepositoryInterface.save(readingsOn);
            readingsOnsWithId.add(newReadingOn);
        }
        return readingsOnsWithId;
    }

    /***********************************************************/
    /**                 GETTERS AND SETTERS                   **/
    /***********************************************************/

    public ReadingsOnRepositoryInterface getReadingsOnRepositoryInterface() {
        return readingsOnRepositoryInterface;
    }

    public void setReadingsOnRepositoryInterface(ReadingsOnRepositoryInterface readingsOnRepositoryInterface) {
        this.readingsOnRepositoryInterface = readingsOnRepositoryInterface;
    }
}
