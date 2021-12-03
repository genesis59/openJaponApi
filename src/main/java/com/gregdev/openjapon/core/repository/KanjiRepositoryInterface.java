package com.gregdev.openjapon.core.repository;

import com.gregdev.openjapon.core.entity.Kanji;
import org.springframework.data.repository.CrudRepository;

public interface KanjiRepositoryInterface extends CrudRepository<Kanji,Long> {
}
