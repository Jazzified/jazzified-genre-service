package dev.tobiadegbuji.jazzifiedgenreservice.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@Log4j2
public class AWSDynamoDBConfig{

    @Bean
    public DynamoDB dynamoDB(){
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
                .build();

       return new DynamoDB(client);
    }

    //Creating table in DynamoDB programmatically
    @Bean
    ApplicationRunner applicationRunner(DynamoDB dynamoDB) {
        return
            args -> {
                String tableName = "Genres";

                try {

                    log.info("Attempting to create table: " + tableName);


                    Table table = dynamoDB.createTable(
                            tableName,
                            Arrays.asList(new KeySchemaElement("genreId", KeyType.HASH), new KeySchemaElement("create-date", KeyType.RANGE)),
                            Arrays.asList(
                                    new AttributeDefinition("genreId", ScalarAttributeType.S),
                                    new AttributeDefinition("create-date", ScalarAttributeType.S)),
                            new ProvisionedThroughput(10L, 10L));

                    table.waitForActive();

                    log.info("Table Status: " + table.getDescription().getTableStatus());

                } catch (Exception e) {

                    log.error("Unable to create table. ");

                    e.printStackTrace();

                }
        };
    }



}
