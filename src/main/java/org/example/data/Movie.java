package org.example.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Movie {
    int id;
    String title;
    short year;
    Short duration;
    String synopsis;
    String posterUri;
}
