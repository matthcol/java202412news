package org.example;

import org.example.tool.MiscTools;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DemoVariableInference {

    @Test
    void demoVariables(){
        String city = "Pau";
        int population = 77_000;

        // Java 5-6
        List<String> cities = new ArrayList<String>();
        Collections.addAll(cities, "Pau", "Toulouse", "Lille", "Montauban");
        // Java 7+
        List<String> cities2 = new ArrayList<>();

        // Java 10+
        var date = LocalDate.of(2024, 2, 29);
        for (var aCity: cities){
            System.out.println("\t- " + aCity);
        }
        var complexCollection = MiscTools.dummyComplexFunction();
        var cities3 = new ArrayList<String>();

        System.out.println(city);
        System.out.println(population);
        System.out.println(cities);
        System.out.println(cities2);
        System.out.println(cities3);
        System.out.println(date);
        System.out.println(complexCollection);
    }
}
