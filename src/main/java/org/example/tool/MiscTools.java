package org.example.tool;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MiscTools {

    public static Map<String, List<Set<String>>> dummyComplexFunction(){
        return new HashMap<>();
    }

    public static String encodeCity(String city){
        return MessageFormat.format("{0}#{1}",
                city.toUpperCase(),
                city.length()
        );
    }
}
