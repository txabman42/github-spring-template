package com.github.technicaltest.controller

import com.github.technicaltest.application.model.UserDto
import com.github.technicaltest.application.rest.UserApi
import com.github.technicaltest.application.rest.UserApiController
import com.github.technicaltest.domain.model.User
import com.github.technicaltest.domain.service.UserService
import spock.lang.Shared
import spock.lang.Specification

import java.time.ZoneOffset

import static org.springframework.http.HttpStatus.OK

class UserApiControllerSpec extends Specification {

    UserService userService = Mock(UserService)
    UserApi userApi = new UserApiController(userService)

    @Shared
    User user
    UserDto userDto

    def setup() {
        user = new User(UUID.randomUUID(), "Dummy User",
                new Date().toInstant().atOffset(ZoneOffset.UTC))
        userDto = new UserDto(user.uuid, user.name, user.creationDate)
    }

    def "given valid id when findUser is performed should return valid userDto"() {
        given:
            1 * userService.findByUuid(user.uuid) >> user
        when:
            def result = userApi.findUser(user.uuid)
        then:
            result.statusCode == OK
            result.getBody() == userDto
    }

}
