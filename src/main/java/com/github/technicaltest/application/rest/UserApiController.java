package com.github.technicaltest.application.rest;

import com.github.technicaltest.application.model.UserDto;
import com.github.technicaltest.domain.model.User;
import com.github.technicaltest.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserApiController implements UserApi {

    private final UserService userService;

    @Override
    public ResponseEntity<UserDto> findUser(final UUID id) {
        log.info("[ GET ] -> /users/{}", id);
        User user = userService.findByUuid(id);
        return ResponseEntity.ok(UserDto.of(user));
    }
}
