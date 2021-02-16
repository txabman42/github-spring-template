package com.github.technicaltest.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.technicaltest.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class UserDto {

    @NotNull
    @JsonProperty("id")
    private UUID id;

    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("creationDate")
    private OffsetDateTime creationDate;

    public static UserDto of(User user) {
        return UserDto.builder()
                .id(user.getUuid())
                .name(user.getName())
                .creationDate(user.getCreationDate())
                .build();
    }
}
