package com.github.technicaltest.support

import org.springframework.stereotype.Service

@Service
class DummyServiceImpl implements DummyService {

    @Override
    void mockException() {}
}
