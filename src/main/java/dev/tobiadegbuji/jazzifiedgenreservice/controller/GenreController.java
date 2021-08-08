package dev.tobiadegbuji.jazzifiedgenreservice.controller;


import dev.tobiadegbuji.jazzifiedgenreservice.domain.Genre;
import dev.tobiadegbuji.jazzifiedgenreservice.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/jazzified")
@AllArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @PostMapping("/genre")
    public ResponseEntity<Genre> createGenre(@RequestBody @Valid Genre genre){

        Genre savedGenre = genreService.createSingleGenre(genre);

        return new ResponseEntity<>(savedGenre, HttpStatus.CREATED);

    }



}


