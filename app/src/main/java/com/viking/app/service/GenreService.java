package com.viking.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.viking.app.model.Genre;
import com.viking.app.repository.GenreRepository;

@Service
public class GenreService {
    
    @Autowired
    GenreRepository genreRepository;

    public Page<Genre> findAll(Pageable pageable, String flag){
        if(flag != null && flag.equals("all")){
            return genreRepository.findAll(pageable);
        }else if(flag != null){
            return genreRepository.findByName(flag, pageable);
        }else{
            return genreRepository.findAll(pageable);
        }
    }
    
    public Optional<Genre> findById(Integer id){
        return genreRepository.findById(id);
    }

    public Genre save(Genre genre){
        return genreRepository.save(genre);
    }

    public void delete(Genre genre){
        genreRepository.delete(genre);
    }
}
