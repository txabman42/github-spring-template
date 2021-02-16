package com.github.technicaltest.support

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackages = "com.github.technicaltest")
class DummyApplication {
    static void main(String[] args) {
        SpringApplication.run(DummyApplication.class, args)
    }
}

