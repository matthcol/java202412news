package org.example.fixture;

import org.example.data.Movie;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.provider.Arguments;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Stream;

public class MovieComparatorSource {

    /**
     * Provide different comparators of movies to use with @MethodSource
     * in a test accepting a Comparator<Movie>
     * NB: type Arguments allow to wrap comparator with a name used by
     * test platform to tag each test case
     */
    public static Stream<Arguments> source(){
        var collatorEnglish = Collator.getInstance(Locale.ENGLISH);
        return Stream.of(
                Arguments.of(Named.of("year asc",
                        Comparator.comparing(Movie::getYear))),
                Arguments.of(Named.of("year asc, title asc",
                        Comparator.comparing(Movie::getYear)
                        .thenComparing(Movie::getTitle)
                )),
                Arguments.of(Named.of("year desc, title asc CI",
                        Comparator.comparingInt(Movie::getYear).reversed()
                                .thenComparing(Movie::getTitle, String::compareToIgnoreCase)
                )),
                Arguments.of(Named.of("title asc (EN), year asc",
                        Comparator.comparing(Movie::getTitle, collatorEnglish::compare)
                                .thenComparingInt(Movie::getYear)
                )),
                Arguments.of(Named.of("duration desc, title asc (CI)",
                        Comparator.comparing(Movie::getDuration,
                                        Comparator.nullsLast(Comparator.reverseOrder())
                                )
                                .thenComparing(Movie::getTitle, String.CASE_INSENSITIVE_ORDER)
                ))

        );
    }
}
