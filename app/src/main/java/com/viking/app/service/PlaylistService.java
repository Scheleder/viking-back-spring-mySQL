package com.viking.app.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.viking.app.model.Playlist;
import com.viking.app.repository.PlaylistRepository;

@Service
public class PlaylistService {

    @Autowired
    PlaylistRepository playlistRepository;

    public Page<Playlist> findAll(Pageable pageable, String flag){
        if(flag != null && flag.equals("next")){
            return playlistRepository.findByDateAfterOrderByDateAsc(LocalDateTime.now(), pageable);
        }else if(flag != null && flag.equals("previous")){
            return playlistRepository.findByDateBeforeOrderByDateDesc(LocalDateTime.now(), pageable);
        }else if(flag != null && flag.equals("all")){
            return playlistRepository.findAll(pageable);
        }else if(flag != null){
            return playlistRepository.findByStyle(flag, pageable);
        }else{
            return playlistRepository.findAll(pageable);
        }
    }
    
    public Optional<Playlist> findById(Integer id){
        return playlistRepository.findById(id);
    }

    public Playlist save(Playlist playlist){
        return playlistRepository.save(playlist);
    }

    public void delete(Playlist playlist){
        playlistRepository.delete(playlist);
    }
}
