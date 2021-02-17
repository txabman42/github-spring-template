package com.github.technicaltest.domain.service

import com.github.technicaltest.application.rest.configuration.exception.GithubNotFoundException
import com.github.technicaltest.domain.model.User
import com.github.technicaltest.domain.repository.UserRepository
import com.github.technicaltest.domain.service.UserService
import com.github.technicaltest.domain.service.UserServiceImpl
import spock.lang.Shared
import spock.lang.Specification

import java.time.ZoneOffset

class UserServiceImplSpec extends Specification {

    UserRepository userRepository = Mock(UserRepository)
    UserService userService = new UserServiceImpl(userRepository)

    @Shared
    User user

    void setup() {
        user = new User(UUID.randomUUID(), "Dummy User",
                new Date().toInstant().atOffset(ZoneOffset.UTC))
    }

    def "when id of existing user is provided then findByUuid should return valid user"() {
        given:
            1 * userRepository.findById(user.uuid) >> Optional.of(user)
        when:
            def result = userService.findByUuid(user.uuid)
        then:
            result == user
    }

    def "when id of non-existing user is provided then findByUuid should return empty optional"() {
        given:
            1 * userRepository.findById(user.uuid) >> Optional.empty()
        when:
            userService.findByUuid(user.uuid)
        then:
            thrown(GithubNotFoundException)
    }
}
