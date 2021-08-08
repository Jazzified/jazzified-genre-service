package dev.tobiadegbuji.jazzifiedgenreservice.service;

import dev.tobiadegbuji.jazzifiedgenreservice.domain.Genre;
import dev.tobiadegbuji.jazzifiedgenreservice.repository.GenreRepo;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class GenreService {

    private final GenreRepo genreRepo;

    public Genre createSingleGenre(Genre genre) {

        genre.setDateTime(LocalDateTime.now());

        genreRepo.saveSingleGenre(genre);

        return genre;

    }


}
