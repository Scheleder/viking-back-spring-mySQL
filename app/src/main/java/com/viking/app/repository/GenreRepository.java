package com.viking.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.viking.app.model.Genre;

public interface GenreRepository extends PagingAndSortingRepository<Genre, Integer>{
    Page<Genre> findByName(String name, Pageable pageable);
}
