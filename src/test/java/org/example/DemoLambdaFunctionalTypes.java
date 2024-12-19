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
        var f1 = movie -> movie.getYear() == 1984;
        var f2 = movie -> movie.getYear() / 10;
        var f3 = x -> x * x + 1;
        var f4 = year -> Movie.builder().title("Dummy Movie").year(year).build();
        var f5 = movie -> Movie.builder()
                .title(movie.getTitle())
                .year(movie.getYear())
                .build();
    }

}
