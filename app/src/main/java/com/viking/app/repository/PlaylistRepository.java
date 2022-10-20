package com.viking.app.repository;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.viking.app.model.Playlist;


public interface PlaylistRepository extends PagingAndSortingRepository<Playlist, Integer>{
    Page<Playlist> findByDateAfterOrderByDateAsc(LocalDateTime date, Pageable pageable);
    Page<Playlist> findByDateBeforeOrderByDateDesc(LocalDateTime date, Pageable pageable);
    Page<Playlist> findByStyle(String style, Pageable pageable);
}

