package org.example.data;

import java.time.LocalDate;
import java.util.List;

public class Actor extends Person {
    private List<Movie> filmography;

    public Actor(List<Movie> filmography) {
        this.filmography = filmography;
    }

    public Actor(String name, List<Movie> filmography) {
        super(name);
        this.filmography = filmography;
    }

    public Actor(int id, String name, List<Movie> filmography) {
        super(id, name);
        this.filmography = filmography;
    }

    public Actor(int id, String name, LocalDate birthdate, List<Movie> filmography) {
        super(id, name, birthdate);
        this.filmography = filmography;
    }

    public List<Movie> getFilmography() {
        return filmography;
    }

    public void setFilmography(List<Movie> filmography) {
        this.filmography = filmography;
    }
}
