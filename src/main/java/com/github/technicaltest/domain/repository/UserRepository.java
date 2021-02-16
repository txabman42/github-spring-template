package com.github.technicaltest.domain.repository;

import com.github.technicaltest.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

    /**
     * Finds persisted user by uuid
     *
     * @param uuid User's uuid
     */
    Optional<User> findById(final UUID uuid);
}
