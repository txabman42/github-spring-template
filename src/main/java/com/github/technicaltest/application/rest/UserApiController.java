package com.github.technicaltest.application.rest;

import com.github.technicaltest.application.model.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserApiController implements UserApi {

    @Override
    public ResponseEntity<UserDto> findUser(final UUID id) {
        return null;
    }
}
