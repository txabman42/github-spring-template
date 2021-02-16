package com.github.technicaltest.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class User {

    private UUID uuid;
    private String name;
    private OffsetDateTime creationDate;
}
