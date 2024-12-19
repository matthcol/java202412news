package org.example;

import org.example.data.Movie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;
import java.util.stream.Stream;

public class DemoLambdaFunctionalTypes {

    @Test
    void demoLambdaNoParameter(){
        // Suppliers: () -> T|int|long|double
        IntSupplier f1 = () -> 12;  // type profile: () -> int
        LongSupplier f2 = () -> 10_000_000_000L;
        DoubleSupplier f3 = () -> 2.5;
        Supplier<String> f4 = () -> "Java";
        Supplier<List<String>> f5 = ArrayList::new;

        // call functions
        System.out.println(f1.getAsInt());
        System.out.println(f2.getAsLong());
        System.out.println(f3.getAsDouble());
        System.out.println(f4.get());
        System.out.println(f5.get());
    }

    @Test
    void demoLambda1ParameterReturningValue(){
        Function<String, String> f1 = city -> city.substring(0, 3).toUpperCase();
        Function<Movie, String> f2 = movie -> movie.getTitle().substring(0, 5).toUpperCase();
        Function<Movie, String> f3 = Movie::getTitle;
        // call functions
        String r1 = f1.apply("Toulouse");
        var movie = Movie.builder().title("Dune: Part One").build();
        String r2 = f2.apply(movie);
        String r3 = f3.apply(movie);
        Stream.of(r1, r2, r3).forEach(System.out::println);
    }

    @Test
    void demoLambdaGame() {
        Function<Movie, Boolean> f1 = movie -> movie.getYear() == 1984;
        // Better: Predicate (return type = bool)
        Predicate<Movie> f1bis = movie -> movie.getYear() == 1984;

        Function<Movie, Integer> f2 = movie -> movie.getYear() / 10;
        Function<Movie, Short> f2bis = movie -> (short) (movie.getYear() / 10);

        // Movie -> int
        ToIntFunction<Movie> f2ter = movie -> movie.getYear() / 10; // return type is int

        // int -> Movie
        IntFunction<Movie> f3 = year -> Movie.builder()
                .title("Dummy Movie")
                .year((short) year)
                .build();

        Function<Integer, Integer> f4 = x -> x * x + 1;
        UnaryOperator<Integer> f4bis = x -> x * x + 1;
        IntUnaryOperator f4ter = x -> x * x + 1;

        Function<Movie, Movie> f5 = movie -> Movie.builder()
                .title(movie.getTitle())
                .year(movie.getYear())
                .build();
        UnaryOperator<Movie> f5bis = movie -> Movie.builder()
                .title(movie.getTitle())
                .year(movie.getYear())
                .build();
    }

}
