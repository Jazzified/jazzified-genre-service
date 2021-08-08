package dev.tobiadegbuji.jazzifiedgenreservice.repository;


import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import dev.tobiadegbuji.jazzifiedgenreservice.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

import static dev.tobiadegbuji.jazzifiedgenreservice.utils.Constants.GENRE_TABLE_NAME;

@Repository
@AllArgsConstructor
@Log4j2
public class GenreRepo {

    private final DynamoDB dynamoDB;

    public void saveSingleGenre(Genre genre) {

       Table table = dynamoDB.getTable(GENRE_TABLE_NAME);

        final Map<String, Object> infoMap = new HashMap<>();

        infoMap.put("create-date", genre.getDateTime().toString());

        if(genre.getGenreInfo() != null ) {
            if (genre.getGenreInfo().getDescription() != null)
                infoMap.put("description", genre.getGenreInfo().getDescription());
            if (genre.getGenreInfo().getParentGenre() != null)
                infoMap.put("parent-genre", genre.getGenreInfo().getParentGenre());
        }

        try {
            log.info(() -> "Adding a new item...");

            PutItemOutcome outcome = table
                    .putItem(new Item().withPrimaryKey("genre-name", genre.getName())
                            .withMap("info", infoMap));

            log.info(() -> "PutItem succeeded:\n" + outcome.getPutItemResult());

        }
        catch (Exception e) {
            log.error("Could not add item with key provided");

            e.printStackTrace();
        }


    }
}
