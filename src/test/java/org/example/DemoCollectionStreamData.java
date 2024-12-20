package org.example;

import org.example.converter.CsvMovie;
import org.example.converter.CsvPerson;
import org.example.data.Movie;
import org.example.data.Person;
import org.example.function.TriPredicate;
import org.example.tool.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.Collator;
import java.text.MessageFormat;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Test
    void demoStream1(){
        personLines.stream()
                .skip(200)
                .limit(10)
                .forEach(System.out::println);
    }

    // Exercise: Convert all lines into a List<Person>
    // using Person lineToPerson(String line) (class CsvPerson)
    @Test
    void demoCollectListPerson(){
        List<Person> persons = personLines.stream()
                .map(CsvPerson::lineToPerson)
                .toList();
        persons.stream()
                .limit(5)
                .forEach(System.out::println);
    }


    // Exercise: idem with movies
    @Test
    void demoCollectListMovie(){
        List<Movie> movies = movieLines.stream()
                .map(CsvMovie::lineToMovie)
                .collect(Collectors.toCollection(ArrayList::new));
        movies.stream()
                .limit(5)
                .forEach(System.out::println);
    }

    // Exercise: count movies and movies from year 1984
    @Test
    void demoCount(){
        long nbMovie1984 = movieLines.stream()
                .map(CsvMovie::lineToMovie)
                .filter((Movie movie) -> movie.getYear() == 1984)
                .peek(System.out::println)
                .count();
        System.out.println(MessageFormat.format("Movies: total = {0}; year 1984 = {1}",
                movieLines.size(),
                nbMovie1984
        ));
    }

    // Exercise: filter movies by year = 1984,
    //      + verify year with peek,
    //      + extract title,
    //      + sort titles
    //      + concatenate titles with separator ', '
    @Test
    void demoCollectionStreamData4() {
        String titles = movieLines.stream()
                .map(CsvMovie::lineToMovie)
                .filter(movie -> movie.getYear() == 1984)
                .peek(l -> System.out.println("Step 1- filtre sur année: " + l))
                .map(Movie::getTitle)
                .peek(l -> System.out.println("Step 2 - extract titre: " + l))
                .sorted() // collect to sort then stream again
                .peek(l -> System.out.println("Step 3 - after sort: " + l))
                .collect(Collectors.joining(", "));
        System.out.println();
        System.out.println(titles);
    }


    // Exercise: group movies by year:
    //      * count movies
    //      * list  movies

    // Exercise: idem by decade


    @Test
    void demoFilterBounds(){
        short year1 = 1980;
        short year2 = 1989;
        var movie1 = Movie.builder()
                .title("A")
                .year(year1)
                .build();
        var movie2 = Movie.builder()
                .title("F")
                .year(year2)
                .build();
        // filter
        TriPredicate<Movie, Movie, Movie> predicate = (m, m1, m2) ->
                (m.getYear() >= m1.getYear())
                && (m.getYear() <= m2.getYear())
                && (m.getTitle().compareTo(m1.getTitle()) >= 0)
                && (m.getTitle().compareTo(m2.getTitle()) < 0);

        var step1 = movieLines.stream()
                .map(CsvMovie::lineToMovie);
        var step2 = StreamUtils.filterBounds(step1, movie1, movie2, predicate);
        step2.forEach(System.out::println);
    }


    // Sorting
    // - primitive types: < + == (natural order)
    // - object type:
    //      * implements Comparable (natural order)
    //      * external: Comparator (FunctionalInterface)

    @Test
    void demoSortIntroPrimitive(){
        int[] numberArray = { 12, 23, 7, 8, 55, -1, 101};
        List<Integer> numberList = Arrays.stream(numberArray)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        NavigableSet<Integer> numberSet = new TreeSet<>(numberList); // natural order of type int
        System.out.println("A: " + Arrays.toString(numberArray));
        System.out.println("L: " + numberList);
        System.out.println("S: " + numberSet);
        System.out.println();

        // sorting array
        Arrays.sort(numberArray); // natural order of type int

        // sorting list
        // - sol1: new list
        var numberListSorted = numberList.stream()
                .sorted()
                .toList();
        // - sol2: in place
        Collections.sort(numberList);
        // numberList.sort(?); // needs a comparator
        System.out.println("A: " + Arrays.toString(numberArray));
        System.out.println("L (new): " + numberListSorted);
        System.out.println("L (old): " + numberList);
    }

    @Test
    void demoSortIntroComparable(){
        Stream.of("Toulouse", "Montauban", "Lille")
                .sorted() // natural order of type String: override of method Comparable.compareTo
                .forEach(System.out::println);
        System.out.println();

        Stream.of("Toulouse", "Montauban", "Lille", "lyon")
                .sorted() // natural order of type String: override of method Comparable.compareTo
                .forEach(System.out::println);
        System.out.println();
    }

    @Test
    void demoSortIntroComparator(){
        Stream.of("Toulouse", "Montauban", "Lille", "lyon")
                .sorted(String::compareToIgnoreCase)
                .forEach(System.out::println);
    }

    @Test
    void demoSortIntroComparator2(){
        var locale = Locale.getDefault(); // fr_FR
        System.out.println(locale);
        locale = Locale.FRENCH;
        System.out.println(locale);
        locale = Locale.of("fr", "FR");
        System.out.println(locale);
        System.out.println();
        var collator = Collator.getInstance(locale);

        Stream.of(
                "été", "étuve", "étage",
                        "cœur", "cobra", "corde",
                        "hameçon",
                        "garçon", "garde", "Garage"
                )
                .sorted(collator::compare)
                .forEach(System.out::println);
    }

    // comparator before Java8 (or anonymous class)
    class StringComparatorCaseInsensitive implements Comparator<String>{
        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }
    }

    @Test
    void demoSortIntroComparatorBeforeJava8(){
        Comparator<String> cmp = new StringComparatorCaseInsensitive();
        Stream.of("Toulouse", "Montauban", "Lille", "lyon")
                .sorted(cmp)
                .forEach(System.out::println);
    }




    // sort movies



    @ParameterizedTest
    @MethodSource("org.example.fixture.MovieComparatorSource#source")
    void demoSortMovies(Comparator<Movie> cmp){

        // use comparator with:
        // Collections.sort
        // List.sort
        // Stream.sorted()
        // SortedSet/NavigableSet
        var sortedMovies = movieLines.stream()
                .map(CsvMovie::lineToMovie)
                // .sorted() // java.lang.ClassCastException: class org.example.data.Movie cannot be cast to class java.lang.Comparable
                .sorted(cmp)
                .toList();

        // display as sample
        sortedMovies.stream()
                .limit(10)
                .forEach(System.out::println);
        System.out.println("...");
        sortedMovies.stream()
                .skip(500)
                .limit(20)
                .forEach(System.out::println);
        System.out.println("...");
        System.out.println(sortedMovies.getLast());
    }

    @Test
    void demoFilterNull(){
        var nbMovieWithNoDuration = movieLines.stream()
                .map(CsvMovie::lineToMovie)
                .map(Movie::getDuration)
                .filter(Objects::isNull)  // Java 7: Objects
                .count();

        var nbMovieWithDuration = movieLines.stream()
                .map(CsvMovie::lineToMovie)
                .map(Movie::getDuration)
                .filter(Objects::nonNull)  // Java 8: add this method to Objects
                .count();
        System.out.println("Movies with duration: " + nbMovieWithDuration);
        System.out.println("Movies without duration: " + nbMovieWithNoDuration);
    }


    // Optional Demo part

    @ParameterizedTest
    @CsvSource({
            "8351, 1",
            "0, 1",
            "8351, -1",
            "0, -1",
    })
    void demoOptional_bothMoviePersonFound(int idMovie, int idPerson){
        var optPerson = EntityOptionalUtils.findById(
                personLines.stream().map(CsvPerson::lineToPerson),
                idPerson
        );
        var optMovie = EntityOptionalUtils.findById(
                movieLines.stream().map(CsvMovie::lineToMovie),
                idMovie
        );
        // display optional boxes
        System.out.println(optPerson);
        System.out.println(optMovie);

        // imperative way
        System.out.println();
        if (optMovie.isPresent()) { // or optMovie.isEmpty() for opposite case
            var movie = optMovie.get();
            System.out.println("Movie found: " + movie.getTitle() + " " + movie.getYear()); // TODO: formatted string
        } else {
            System.out.println("No movie found");
        }

        // functional way
        System.out.println();
        var movieResult = optMovie.orElse(new Movie());
        System.out.println("Movie or default: " + movieResult);

        System.out.println();
        optMovie.ifPresent(movie -> System.out.println("Movie found (1): " + movie.getTitle()));
        optMovie.ifPresentOrElse(
                movie -> System.out.println("Movie found (2): " + movie.getTitle()),
                () -> System.out.println("No movie found (2)")
        );

        optMovie.ifPresent(MoviePersistenceUtils::persistMovie);

        System.out.println();
        var optTitle = optMovie.map(Movie::getTitle);
        System.out.println("Optional title: " + optTitle);

        System.out.println();
        Optional<Map<String, String>> optInfo = optMovie.flatMap(
                movie -> optPerson.map(
                        person -> Map.of(
                                "title", movie.getTitle(),
                                "name", person.getName()
                        )
                )
        );
        System.out.println("Optional infos: " + optInfo);

        // transform entity found or else throw
        System.out.println();
        var movie2 = optMovie.orElseThrow(IllegalArgumentException::new);
        var result2 = MessageFormat.format("{0} ({1}}", movie2.getTitle(), movie2.getYear());
        System.out.println("Movie found transformed: " + result2);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Toulouse"})
    @NullSource
    void demoOptionalBuild(String city){
        // source sure
        var optCity = Optional.of("Lille");
        // source soit ok soit null
        var optCity2 = Optional.ofNullable(city);
        // empty box
        var optCity3 = Optional.empty();
        Stream.of(optCity, optCity2, optCity3)
                .forEach(System.out::println);
        // source soit ok soit null, cas null => exception
        var optCity4 = Optional.of(city);
        System.out.println(optCity4);
    }

}
