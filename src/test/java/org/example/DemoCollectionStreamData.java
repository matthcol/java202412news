package org.example;

import org.example.converter.CsvMovie;
import org.example.converter.CsvPerson;
import org.example.data.Movie;
import org.example.data.Person;
import org.example.function.TriPredicate;
import org.example.tool.CsvUtils;
import org.example.tool.FilePathResourceUtils;
import org.example.tool.StreamUtils;
import org.junit.jupiter.api.*;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NavigableSet;
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
    @Test
    void demoCount(){
        long nbMovie1984 = movieLines.stream()
                .map(CsvMovie::lineToMovie)
                .filter((Movie movie) -> movie.getYear() == 1984)
                .peek(System.out::println)
                .count();
        System.out.println(MessageFormat.format("Movies: total = {0}; year 1984 = {1}",
                movieLines.size(),
                nbMovie1984
        ));
    }

    // Exercise: filter movies by year = 1984,
    //      + verify year with peek,
    //      + extract title,
    //      + sort titles
    //      + concatenate titles with separator ', '
    @Test
    void demoCollectionStreamData4() {
        String titles = movieLines.stream()
                .map(CsvMovie::lineToMovie)
                .filter(movie -> movie.getYear() == 1984)
                .peek(l -> System.out.println("Step 1- filtre sur année: " + l))
                .map(Movie::getTitle)
                .peek(l -> System.out.println("Step 2 - extract titre: " + l))
                .sorted() // collect to sort then stream again
                .peek(l -> System.out.println("Step 3 - after sort: " + l))
                .collect(Collectors.joining(", "));
        System.out.println();
        System.out.println(titles);
    }


    // Exercise: group movies by year:
    //      * count movies
    //      * list  movies

    // Exercise: idem by decade


    @Test
    void demoFilterBounds(){
        short year1 = 1980;
        short year2 = 1989;
        var movie1 = Movie.builder()
                .title("A")
                .year(year1)
                .build();
        var movie2 = Movie.builder()
                .title("F")
                .year(year2)
                .build();
        // filter
        TriPredicate<Movie, Movie, Movie> predicate = (m, m1, m2) ->
                (m.getYear() >= m1.getYear())
                && (m.getYear() <= m2.getYear())
                && (m.getTitle().compareTo(m1.getTitle()) >= 0)
                && (m.getTitle().compareTo(m2.getTitle()) < 0);

        var step1 = movieLines.stream()
                .map(CsvMovie::lineToMovie);
        var step2 = StreamUtils.filterBounds(step1, movie1, movie2, predicate);
        step2.forEach(System.out::println);
    }


    // Sorting

    @Test
    void demoSortIntro(){
        int[] numberArray = { 12, 23, 7, 8, 55, -1, 101};
        List<Integer> numberList = null;
        NavigableSet<Integer> numberSet = null;

        // TODO: sort array, list, sorted set
        Arrays.sort(numberArray); // natural order of type int
        System.out.println(Arrays.toString(numberArray));
    }











}
