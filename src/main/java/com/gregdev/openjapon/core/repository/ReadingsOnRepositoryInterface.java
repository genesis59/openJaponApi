package com.gregdev.openjapon.core.repository;

import com.gregdev.openjapon.core.entity.ReadingsOn;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReadingsOnRepositoryInterface extends PagingAndSortingRepository<ReadingsOn, Long> {
    Iterable<ReadingsOn> findByReadingOn(String readingOn);
}
