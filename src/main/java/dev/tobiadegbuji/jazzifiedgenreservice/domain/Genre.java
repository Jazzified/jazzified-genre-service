package dev.tobiadegbuji.jazzifiedgenreservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.joda.time.DateTime;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Genre {

    @NotBlank
    private String name;

    private LocalDateTime dateTime = LocalDateTime.now();

    private GenreInfo genreInfo;

}
