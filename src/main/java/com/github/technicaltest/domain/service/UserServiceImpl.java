package com.github.technicaltest.domain.service;

import com.github.technicaltest.application.rest.configuration.exception.GithubNotFoundException;
import com.github.technicaltest.domain.model.User;
import com.github.technicaltest.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String USER_NOT_FOUND = "User with uuid '%s' not found";

    private final UserRepository userRepository;

    @Override
    public User findByUuid(final UUID uuid) {
        return userRepository.findById(uuid).orElseThrow(
                () -> new GithubNotFoundException(String.format(USER_NOT_FOUND, uuid)));
    }
}
