package com.gregdev.openjapon.core.repository;

import com.gregdev.openjapon.core.entity.Kanji;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface KanjiRepositoryInterface extends PagingAndSortingRepository<Kanji, Long> {
    Page<Kanji> findByStrokes(Byte strokes, Pageable pageable);

    Page<Kanji> findByIdIn(List<Long> idList, Pageable pageable);

    Page<Kanji> findByIdInAndStrokes(List<Long> idList, Byte strokes, Pageable pageable);
}
