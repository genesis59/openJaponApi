package com.gregdev.openjapon.core.repository;

import com.gregdev.openjapon.core.entity.ReadingsKun;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReadingsKunRepositoryInterface extends PagingAndSortingRepository<ReadingsKun, Long> {
    Iterable<ReadingsKun> findByReadingKun(String readingKun);
}
