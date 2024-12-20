package org.example.tool;

import org.example.data.EntityWithIntId;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Java 7+: Objects toolbox (isNull, nonNull, equals, toString, hash)
 * Java 8: Optional<T>, OptionalInt, OptionalDouble, OptionalLong
 *
 */

public class EntityOptionalUtils {

    public static <T extends EntityWithIntId> Optional<T> findById(Stream<T> stream, int id){
        return stream.filter(entity -> entity.getId() == id)
                .findFirst();
    }
}
