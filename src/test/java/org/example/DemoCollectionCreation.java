package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class DemoCollectionCreation {

    @Test
    void createCollections(){
        // Java 9+
        var cities = List.of("Pau", "Toulouse", "Lille", "Montauban");
        System.out.println(cities);

        var numbers = Set.of(1, 4, 7, 8);
        System.out.println(numbers);

        var indexCity = Map.of(
                "64000", "Pau",
                "31000", "Toulouse",
                "59000", "Lille",
                "82000", "Montauban"
        );
        System.out.println(indexCity);
        Stream.of(cities, numbers, indexCity)
                .forEach(collection -> System.out.println(collection.getClass()));

        // Result:
        //        class java.util.ImmutableCollections$ListN
        //        class java.util.ImmutableCollections$SetN
        //        class java.util.ImmutableCollections$MapN

        // ! Trap: all these collections are immutable
        Assertions.assertThrows(
                UnsupportedOperationException.class,
                () ->  cities.add("Imaginary City")
        );
    }
}
