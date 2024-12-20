package org.example.fixture;

import org.example.data.Movie;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Comparator;
import java.util.stream.Stream;

public class MovieComparatorSource {

    public static Stream<Arguments> source(){
        return Stream.of(
                Arguments.of(Named.of("year asc",
                        Comparator.comparing(Movie::getYear))),
                Arguments.of(Named.of("year asc, title asc",
                        Comparator.comparing(Movie::getYear)
                        .thenComparing(Movie::getTitle)))
                // TODO: other comparisons

                // year desc, title case insensitive
                // title locale en, year
                // duration desc, title case insensitive
        );
    }
}
