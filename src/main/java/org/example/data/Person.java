package org.example.data;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Objects;


public class Person implements EntityWithIntId {

    private int id;

    private String name;

    private LocalDate birthdate;

    public Person() {
        this.id = -1;
    }

    public Person(String name) {
        this();
        this.name = name;
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person(int id, String name, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return (new StringBuilder("Person{"))
            .append("id=").append(id)
            .append(", name='").append(name).append('\'')
            .append(", birthdate=").append(birthdate)
            .append('}')
            .toString();
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public boolean hasBirthdate() {
        return Objects.nonNull(birthdate);
    }

    public int getBirthYear(){
        if (!hasBirthdate()) throw new NoSuchElementException("birthdate");
        return getBirthdate().getYear();
    }

    public boolean bornThisYear(int year){
        return hasBirthdate() && (getBirthYear() == year);
    }

    public boolean bornBetweenYears(int year1, int year2){
        return hasBirthdate() && (year1 <= getBirthYear()) && (getBirthYear() <= year2);
    }
}
