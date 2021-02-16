package com.github.technicaltest.controller

import com.github.technicaltest.application.rest.UserApiController
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class UserApiControllerSpec extends Specification {

    UserApiController userApiController = new UserApiController()

    @Shared
    UUID userId

    def setup() {
        userId = UUID.randomUUID()
    }

    @Unroll
    def "given valid id when findUser is performed should return valid userDto"() {
        when:
            def result = userApiController.findUser(userId)
        then:
            result == null
    }
}
