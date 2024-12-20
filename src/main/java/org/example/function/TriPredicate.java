package org.example.function;

/**
 * Functional type with profile: T x U x V -> boolean
 */
@FunctionalInterface
public interface TriPredicate<T,U,V> {
    boolean test(T t, U u, V v);
}
