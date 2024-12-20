package org.example;

import org.example.tool.FilePathResourceUtils;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.IntStream;

public class DemoTryWithResources {
    // séparer les 2 problématiques orthogonales
    // - open/close resource (file, network, cursor bdd, ...)
    // - gérer les exceptions

    // before: try catch finally (try catch)


    @Test
    void demoFile() throws IOException {
        Path pathPersons = FilePathResourceUtils.pathFromResource(DemoTryWithResources.class, "/persons.tsv");
        Path pathMovies = FilePathResourceUtils.pathFromResource(DemoTryWithResources.class, "/movies.tsv");
        System.out.println(pathPersons);
        System.out.println(pathMovies);

        try (
                // resources here: AutoCloseable (better Closeable)
                BufferedReader readerPerson = Files.newBufferedReader(pathPersons);
                BufferedReader readerMovie = Files.newBufferedReader(pathMovies);
        ) {
            // use resource here
            for (int i=0; i<10; i++) {
                String linePerson = readerPerson.readLine();
                String lineMovie = readerMovie.readLine();
                System.out.println(linePerson);
                System.out.println(lineMovie);
            }
        } // attempt: readerMovie.close() + readerPerson.close()

        // handle exceptions here or propagates them

    }
}
