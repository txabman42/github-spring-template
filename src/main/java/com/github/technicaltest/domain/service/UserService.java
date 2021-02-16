package com.github.technicaltest.domain.service;

import com.github.technicaltest.domain.model.User;

import java.util.UUID;

public interface UserService {

    /**
     * Finds user by UUID
     *
     * @param uuid User's exposed unique identifier
     * @return found user
     */
    User findByUuid(UUID uuid);
}
