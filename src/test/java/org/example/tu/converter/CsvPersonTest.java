package org.example.tu.converter;

import org.example.converter.CsvPerson;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CsvPersonTest {

    @Test
    void testLineToInfoMap_birthdatePresent() {
        String line = "1\tFred Astaire\t1899-05-10";
        var infoMap = CsvPerson.lineToInfoMap(line);
        assertEquals(3, infoMap.size(), "info count");
        int id = (int) infoMap.get("id");
        String name = (String) infoMap.get("name");
        LocalDate birthdate = (LocalDate) infoMap.get("birthdate");
        assertEquals(1, id, "id");
        assertEquals("Fred Astaire", name, "name");
        assertEquals(LocalDate.of(1899,5,10), birthdate);
    }

    @Test
    void testLineToInfoMap_birthdateAbsent() {
        String line = "1\tFred Astaire\t";
        var infoMap = CsvPerson.lineToInfoMap(line);
        assertEquals(2, infoMap.size(), "info count");
        int id = (int) infoMap.get("id");
        String name = (String) infoMap.get("name");
        assertEquals(1, id, "id");
        assertEquals("Fred Astaire", name, "name");
    }

    @Test
    void testLineToPerson_birthdatePresent() {
        String line = "1\tFred Astaire\t1899-05-10";
        var person = CsvPerson.lineToPerson(line);
        assertAll(
                () -> assertEquals(1, person.getId(), "id"),
                () -> assertEquals("Fred Astaire", person.getName(), "name"),
                () -> assertEquals(LocalDate.of(1899,5,10), person.getBirthdate(), "birthdate")
        );
    }

    @Test
    void testLineToPerson_birthdateAbsent() {
        String line = "1\tFred Astaire\t";
        var person = CsvPerson.lineToPerson(line);
        assertAll(
                () -> assertEquals(1, person.getId(), "id"),
                () -> assertEquals("Fred Astaire", person.getName(), "name"),
                () -> assertNull(person.getBirthdate(), "birthdate")
        );
    }
}