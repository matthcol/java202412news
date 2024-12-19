package org.example;

import org.example.converter.CsvMovie;
import org.example.converter.CsvPerson;
import org.example.data.Movie;
import org.example.data.Person;
import org.example.tool.CsvUtils;
import org.example.tool.FilePathResourceUtils;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class DemoCollectionStreamData {
    static List<String> personLines;
    static List<String> movieLines;

    @BeforeAll
    static void readData(){
        personLines = CsvUtils.readFileWithHeader(
                FilePathResourceUtils.pathFromResource(DemoCollectionStreamData.class, "/persons.tsv"),
                UnaryOperator.identity()
        );
        movieLines = CsvUtils.readFileWithHeader(
                FilePathResourceUtils.pathFromResource(DemoCollectionStreamData.class, "/movies.tsv"),
                UnaryOperator.identity()
        );
    }

    @Test
    void demoStream1(){
        personLines.stream()
                .skip(200)
                .limit(10)
                .forEach(System.out::println);
    }

    // Exercise: Convert all lines into a List<Person>
    // using Person lineToPerson(String line) (class CsvPerson)
    @Test
    void demoCollectListPerson(){
        List<Person> persons = personLines.stream()
                .map(CsvPerson::lineToPerson)
                .toList();
        persons.stream()
                .limit(5)
                .forEach(System.out::println);
    }


    // Exercise: idem with movies
    @Test
    void demoCollectListMovie(){
        List<Movie> movies = movieLines.stream()
                .map(CsvMovie::lineToMovie)
                .collect(Collectors.toCollection(ArrayList::new));
        movies.stream()
                .limit(5)
                .forEach(System.out::println);
    }

    // Exercise: count movies and movies from year 1984

    // Exercise: filter movies by year = 1984,
    //      + verify year with peek,
    //      + extract title,
    //      + sort titles
    //      + concatenate titles with separator ', '


    // Exercise: group movies by year:
    //      * count movies
    //      * list  movies

    // Exercise: idem by decade

















}
