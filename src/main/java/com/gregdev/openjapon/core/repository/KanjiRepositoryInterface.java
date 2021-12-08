package com.gregdev.openjapon.core.repository;

import com.gregdev.openjapon.core.entity.Kanji;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface KanjiRepositoryInterface extends PagingAndSortingRepository<Kanji,Long> {
    Page<Kanji> findByStrokes(Byte strokes, Pageable pageable);
}
