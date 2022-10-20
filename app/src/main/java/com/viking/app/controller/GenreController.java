package com.viking.app.controller;
import com.viking.app.model.Genre;
import com.viking.app.service.GenreService;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/genres")
public class GenreController {
    
    @Autowired
    GenreService genreService;

    @GetMapping("/")
    public ResponseEntity<Page<Genre>> getAllLives(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                          @RequestParam(required = false) String flag){
        Page<Genre> genrePage = genreService.findAll(pageable, flag);
        if(genrePage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<Page<Genre>>(genrePage, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getOneLive(@PathVariable(value="id") Integer id){
        Optional<Genre> liveO = genreService.findById(id);
        if(liveO.isPresent()) {
            //System.out.println("OK");
            //System.out.println(liveO.get());
            return new ResponseEntity<Genre>(liveO.get(), HttpStatus.OK); 
        }else {
            //System.out.println("Empty");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Genre> saveGenre(@RequestBody @Valid Genre genre) {
        return new ResponseEntity<Genre>(genreService.save(genre), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGenre(@PathVariable(value="id") Integer id) {
        Optional<Genre> genre = genreService.findById(id);
        if(!genre.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            genreService.delete(genre.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
