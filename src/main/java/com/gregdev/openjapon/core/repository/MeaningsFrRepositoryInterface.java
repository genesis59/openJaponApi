package com.gregdev.openjapon.core.repository;

import com.gregdev.openjapon.core.entity.MeaningsFr;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MeaningsFrRepositoryInterface extends PagingAndSortingRepository<MeaningsFr, Long> {
    Iterable<MeaningsFr> findByMeaningFr(String meaningFr);
}
