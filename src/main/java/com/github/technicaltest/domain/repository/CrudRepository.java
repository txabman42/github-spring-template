package com.github.technicaltest.domain.repository;

import java.util.Optional;

public interface CrudRepository<T, ID> {

    /**
     * Finds persisted entity by id
     *
     * @param var1 Entity's id
     */
    Optional<T> findById(ID var1);
}
