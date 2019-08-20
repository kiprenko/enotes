package com.enotes.db;

import java.util.stream.Stream;

/**
 * Base interface for all dao interfaces. Declare necessary methods for DAOs
 * Another methods specific for some entity must be added into it interface.
 * @param <T> entity class
 * @param <L> id class of the entity
 */
public interface Dao<T, L> {
    //TODO add default realization for this methods.
    Stream<T> getAll();
    T getById(L id);
    boolean add(T note);
    boolean update(T note);
    boolean delete(T note);
}
