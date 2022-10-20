package com.viking.app.controller;
import com.viking.app.model.Playlist;
import com.viking.app.service.PlaylistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.time.LocalDateTime;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    PlaylistService playlistService;

    @GetMapping("/")
    public ResponseEntity<Page<Playlist>> getAllLives(@PageableDefault(page = 0, size = 1000, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                          @RequestParam(required = false) String flag){
        Page<Playlist> livePage = playlistService.findAll(pageable, flag);
        if(livePage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            
            return new ResponseEntity<Page<Playlist>>(livePage, HttpStatus.OK);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getOneLive(@PathVariable(value="id") Integer id){
        Optional<Playlist> liveO = playlistService.findById(id);
        if(liveO.isPresent()) {
            //System.out.println("OK");
            //System.out.println(liveO.get());
            return new ResponseEntity<Playlist>(liveO.get(), HttpStatus.OK); 
        }else {
            //System.out.println("Empty");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Playlist> saveLive(@RequestBody @Valid Playlist playlist) {
        playlist.setDate(LocalDateTime.now());
        return new ResponseEntity<Playlist>(playlistService.save(playlist), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLive(@PathVariable(value="id") Integer id) {
        Optional<Playlist> liveO = playlistService.findById(id);
        if(!liveO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            playlistService.delete(liveO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Playlist> updateLive(@PathVariable(value="id") Integer id,
                                                      @RequestBody @Valid Playlist playlist) {
        Optional<Playlist> liveO = playlistService.findById(id);
        if(!liveO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            playlist.setId(liveO.get().getId());
            return new ResponseEntity<Playlist>(playlistService.save(playlist), HttpStatus.OK);
        }
    }
}
