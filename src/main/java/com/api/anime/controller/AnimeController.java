package com.api.anime.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.anime.domain.Anime;
import com.api.anime.service.AnimeService;
import com.api.anime.util.DateUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("anime")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {

	 private final DateUtil dateUtil;
	 
	 @Autowired
	 private AnimeService animeService;
	 
	 @GetMapping("list")
	    public List<Anime> list(){
	        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
	        return  animeService.listAll();
	    }
	 
	 @GetMapping("/{id}")
	    public ResponseEntity<Anime> findById(@PathVariable long id){
	        return ResponseEntity.ok(animeService.findById(id));
	    }
	 
	 @PostMapping
	    public ResponseEntity<Anime> save(@RequestBody Anime anime){
	        return new ResponseEntity<>(animeService.save(anime), HttpStatus.CREATED);
	    }
	 
	 
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(@PathVariable long id) {
	        animeService.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	 
	 @PutMapping
	    public ResponseEntity<Void> replace(@RequestBody Anime anime) {
	        animeService.replace(anime);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
}
