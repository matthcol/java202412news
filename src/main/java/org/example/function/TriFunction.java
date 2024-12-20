package org.example.function;

/**
 * Functional type with profile: T x U x V -> R
 */
@FunctionalInterface
public interface TriFunction<T,U,V,R> {
    R apply(T t, U u, V v);
}
