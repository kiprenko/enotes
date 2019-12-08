package com.enotes.db;

import java.util.List;

/**
 * Base interface for all dao interfaces. Declare necessary methods for DAOs
 * Another methods specific for some entity must be added into it interface.
 * @param <T> entity class
 * @param <L> id class of the entity
 */
public interface Dao<T, L> {
    List<T> findAll();

    T find(L id);

    boolean add(T note);

    boolean update(T note);

    boolean delete(L id);
}
