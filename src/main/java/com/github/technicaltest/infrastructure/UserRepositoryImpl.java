package com.github.technicaltest.infrastructure;

import com.github.technicaltest.domain.model.User;
import com.github.technicaltest.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findById(UUID var1) {
        User defaultUser = new User(var1, "Dummy User",
                new Date().toInstant().atOffset(ZoneOffset.UTC));
        return Optional.of(defaultUser);
    }
}
