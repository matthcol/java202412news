package org.example.converter;



import org.example.data.Movie;

import java.util.HashMap;
import java.util.Map;

public class CsvMovie {
    public static Map<String,Object> lineToInfoMap(String line){
        String[] infos = line.split("\t", -1);
        Map<String, Object> infoMap = new HashMap<>();
        infoMap.put("id", Integer.parseInt(infos[0]));
        infoMap.put("title", infos[1]);
        infoMap.put("year", Short.parseShort(infos[2]));
        if (!infos[3].isEmpty()) infoMap.put("duration", Short.parseShort(infos[3]));
        if (!infos[4].isEmpty()) infoMap.put("synopsis", infos[4]);
        if (!infos[5].isEmpty()) infoMap.put("posterUri", infos[5]);
        return infoMap;
    }

    public static Movie lineToMovie(String line) {
        String[] infos = line.split("\t", -1);
        int id = Integer.parseInt(infos[0]);
        String title = infos[1];
        short year = Short.parseShort(infos[2]);
        var movieBuilder = Movie.builder()
                .id(id)
                .title(title)
                .year(year);
        if (!infos[3].isEmpty()) movieBuilder.duration(Short.parseShort(infos[3]));
        if (!infos[4].isEmpty()) movieBuilder.synopsis(infos[4]);
        if (!infos[5].isEmpty()) movieBuilder.posterUri(infos[5]);
        return movieBuilder.build();
    }

}
