package com.github.technicaltest.application.rest.configuration.exception

import com.github.technicaltest.application.rest.configuration.exception.GithubNotFoundException
import com.github.technicaltest.support.DummyApplication
import com.github.technicaltest.support.DummyService
import org.mockito.MockitoAnnotations
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import javax.validation.ValidationException

import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@AutoConfigureMockMvc
@SpringBootTest(classes = DummyApplication)
class RestResponseEntityExceptionHandlerSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @SpringBean
    DummyService dummyService = Mock(DummyService)

    def setup() {
        MockitoAnnotations.initMocks(this)
    }

    def "User not found exception"() {
        given:
            dummyService.mockException() >> { throw new GithubNotFoundException("") }
        when:
            def result = mockMvc.perform(get("/dummy").contentType(APPLICATION_JSON_VALUE))
                .andReturn().getResponse()
        then:
            result.status == NOT_FOUND.value()
            result.contentType == APPLICATION_JSON_VALUE
    }

    def "Illegal argument exception"() {
        given:
            dummyService.mockException() >> { throw new IllegalArgumentException("") }
        when:
            def result = mockMvc.perform(get("/dummy").contentType(APPLICATION_JSON_VALUE))
                .andReturn().getResponse()
        then:
            result.status == BAD_REQUEST.value()
            result.contentType == APPLICATION_JSON_VALUE
    }

    def "Validation exception"() {
        given:
            dummyService.mockException() >> { throw new ValidationException("") }
        when:
            def result = mockMvc.perform(get("/dummy").contentType(APPLICATION_JSON_VALUE))
                .andReturn().getResponse()
        then:
            result.status == BAD_REQUEST.value()
            result.contentType == APPLICATION_JSON_VALUE
    }

}
