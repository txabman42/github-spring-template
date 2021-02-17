package com.github.technicaltest.application.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.technicaltest.application.model.UserDto
import com.github.technicaltest.application.rest.UserApiController
import com.github.technicaltest.domain.model.User
import com.github.technicaltest.domain.service.UserService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Shared
import spock.lang.Specification

import java.time.ZoneOffset

import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@WebMvcTest(controllers = UserApiController)
class UserApiControllerItSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper

    @SpringBean
    UserService userService = Mock(UserService)

    @Shared
    User user
    UserDto userDto

    def setup() {
        user = new User(UUID.randomUUID(), "Dummy User",
                new Date().toInstant().atOffset(ZoneOffset.UTC))
        userDto = new UserDto(user.uuid, user.name, user.creationDate)
    }

    def "when get by is performed correctly given valid id then response status is '#statusCode'"() {
        given:
            1 * userService.findByUuid(user.uuid) >> user
        when:
            def result = mockMvc.perform(get("/users/" + user.uuid)).andReturn().getResponse()
        then:
            result.status == OK.value()
            result.contentType == APPLICATION_JSON_VALUE
    }

    def "when get by is performed incorrectly given invalid id then response status is '#statusCode'"() {
        when:
            def result = mockMvc.perform(get("/users/" + null)).andReturn().getResponse()
        then:
            result.status == BAD_REQUEST.value()
    }
}
