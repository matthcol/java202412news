package org.example;

import org.example.tool.CsvUtils;
import org.example.tool.FilePathResourceUtils;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.function.UnaryOperator;

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

}
