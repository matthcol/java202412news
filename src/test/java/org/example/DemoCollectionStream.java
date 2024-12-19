package org.example;

import org.example.tool.MiscTools;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DemoCollectionStream {

    static List<String> cities;

    @BeforeAll
    static void initData(){
        cities = List.of("Pau", "Toulouse", "Lille", "Montauban");
    }

    @Test
    void demoStream1(){
        cities.stream() // or  .parallelStream()
                .map(city -> city.toUpperCase()) // map=transform
                .forEach(city -> System.out.println(city)); // final step: side effect
    }

    @ParameterizedTest
    @ValueSource(ints={0, 1, 4, 6, 10})
    void demoStream2(int threshold){
        cities.stream()
                .filter(city -> city.length() < threshold)
                .map(city -> city.toUpperCase())
                .forEach(city -> System.out.println(city));
    }

    @Test
    void demo3primitives_ko(){
        // bad practice
        cities.stream()
                .map(city -> city.length()) // autoboxing: new Integer(city.length())
                .forEach(length -> System.out.println(length));
    }

    @Test
    void demo3primitives_ok(){
        int nbLetters = cities.stream()
                .mapToInt(city -> city.length())
                .filter(length -> length < 6)
                .sum();
        System.out.println("Nb of letters: " + nbLetters);
    }

    @Test
    void demo4primitives_ok(){
        cities.stream()
                .mapToInt(city -> city.length())
                .map(l -> 2 * l + 1)
                .mapToObj(n -> new ArrayList<String>(n))
                .map(array -> array.reversed())
                .forEach(array -> System.out.println(array));
    }

    @Test
    void demo5primitives_detail(){
        var stream = cities.stream()
//                .peek(city -> System.out.println("Step 0 (source) - city: " + city))
                .mapToInt(city -> city.length())
//                .peek(l -> System.out.println("Step 1 - city length: " +l))
                .map(l -> 2 * l + 1)
//                .peek(l -> System.out.println("Step 2 - computation: " +l))
                .filter(l -> l > 15)
//                .peek(l -> System.out.println("Step 3 - filter (keep): " +l))
                .mapToObj(n -> new ArrayList<String>(n))
//                .peek(array -> System.out.println("Step 4 - list creation: " + array))
                .map(array -> array.reversed())
//                .peek(array -> System.out.println("Step 5 - list reversed: " + array))
                ;
        System.out.println(stream);
        // final step: execute full pipeline
        stream.forEach(array -> {
            System.out.println("Final Step - list: " + array);
            System.out.println();
        });
    }


    @Test
    void demo6existingFunctions(){
        cities.stream()
                .map(MiscTools::encodeCity) // ref static method
                .forEach(city -> System.out.println(city));
    }


    @Test
    void demo7collect(){
        var listCityFilteredU = cities.stream()
                .filter(city -> city.length() < 6)
                .map(city -> city.toUpperCase())
                .toList(); // collect (shortcut): since Java 16
        System.out.println(listCityFilteredU);
    }

    @Test
    void demo8collect(){
        var listCityFilteredU = cities.stream()
                .filter(city -> city.length() < 6)
                .map(city -> city.toUpperCase())
                .collect(Collectors.toList()); // Java 8+
        System.out.println(listCityFilteredU);
    }
}

















