package com.github.technicaltest.support

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class DummyController {

    private final DummyService dummyService;

    DummyController(DummyService dummyService) {
        this.dummyService = dummyService
    }

    @RequestMapping(value = "/dummy", method = RequestMethod.GET)
    ResponseEntity<String> getRestrictedEndpoint() {
        dummyService.mockException()
        return ResponseEntity.ok("OK-Restricted")

    }
}