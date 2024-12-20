package org.example.tool;

import org.example.function.TriFunction;
import org.example.function.TriPredicate;

import java.util.stream.Stream;

public class StreamUtils {

    public static <T> Stream<T> filterBounds(
            Stream<T> stream,
            T bound1,
            T bound2,
            TriPredicate<T, T, T> predicate)
    {
        return stream.filter(t -> predicate.test(t, bound1, bound2));
    }
}
