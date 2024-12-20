import org.example.data.Actor;
import org.example.data.Movie;
import org.example.data.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.List;

public class DemoPatternMatching {

    // https://docs.oracle.com/en/java/javase/21/language/pattern-matching-switch.html

    @ParameterizedTest
    @ValueSource(strings = {
            "Java",
            "JavaScript",
            "Python",
            "C#",
            "Cobol",
            "C++",
            "Super mega new language",
            "Z"
    })
    void demoPatternMatchingSimpleData(String choice){
        switch (choice){
            case "Java","C#":
                System.out.println("Compiled into intermediary language (bytecode, ...)");
                break;
            case "Python", "JavaScript":
                System.out.println("Interpreted");
                break;
            case "C++","C","Fortran","Cobol":
                System.out.println("Compiled into binary code");
                break;
            case String c when c.length() > 10:
                System.out.println("Too long");
                break;
            default:
                System.out.println("New Language ?");
        }

    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Java",
            "JavaScript",
            "Python",
            "C#",
            "Cobol",
            "C++",
            "Super mega new language",
            "Z"
    })
    void demoPatternMatchingSimpleData2(String choice){
        switch (choice){
            case "Java","C#" ->
                System.out.println("Compiled into intermediary language (bytecode, ...)");
            case "Python", "JavaScript" ->
                System.out.println("Interpreted");
            case "C++","C","Fortran","Cobol" ->
                System.out.println("Compiled into binary code");
            case String c when c.length() > 10 ->
                System.out.println("Too long");
            default ->
                System.out.println("New Language ?");
        }

    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Java",
            "JavaScript",
            "Python",
            "C#",
            "Cobol",
            "C++",
            "Super mega new language",
            "Z"
    })
    void demoPatternMatchingSimpleDataToExpression(String choice){
        String description =  switch (choice){
            case "Java","C#" -> "Compiled into intermediary language (bytecode, ...)";
            case "Python", "JavaScript" -> "Interpreted";
            case "C++","C","Fortran","Cobol" -> "Compiled into binary code";
            case String c when c.length() > 10 -> "Too long";
            default -> "New Language ?";
        };
        System.out.println(choice + " description: " + description);
    }

    @Test
    void demoPatternMatchingObjects() {
        var data = List.of(
                new Person(1,"John Doe"),
                new Actor(2, "Kevin Costner", List.of(Movie.builder().title("Bodyguard").build())),
                Movie.builder().title("Venom").build(),
                LocalDate.now()
        );
        for (var obj: data){
            switch (obj){
                case Actor a -> System.out.println("Actor: " + a + " ; filmography: " + a.getFilmography());
                // case Person(int id, String name, LocalDate birthdate) when id == 1 -> System.out.println("Person #1: name " + name);
                //  => only allowed with Record
                case Person p -> System.out.println("Person: " + p + " ; name " + p.getName());
                case Movie m -> System.out.println("Movie: " + m + " ; name " + m.getTitle());
                default -> System.out.println("Unknown data type: " + obj.getClass().getSimpleName());
            }
        }

        Person p = (Person) data.get(1);
        // Java 17: instanceof simplified
        if (p instanceof Actor a) {
            System.out.println(a.getFilmography());
        }
    }

}
