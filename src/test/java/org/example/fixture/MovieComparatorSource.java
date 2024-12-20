package org.example.fixture;

import org.example.data.Movie;

import java.util.Comparator;
import java.util.stream.Stream;

public class MovieComparatorSource {

    public static Stream<Comparator<Movie>> source(){
        return Stream.of(
                Comparator.comparing(Movie::getYear),
                Comparator.comparing(Movie::getYear)
                        .thenComparing(Movie::getTitle)
                // TODO: other comparisons

                // year desc, title case insensitive
                // title locale en, year
                // duration desc, title case insensitive
        );
    }
}
