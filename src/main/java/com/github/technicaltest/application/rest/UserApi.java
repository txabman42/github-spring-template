package com.github.technicaltest.application.rest;

import com.github.technicaltest.application.model.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public interface UserApi {

    @Operation(summary = "Search user by id", description = "By passing in the appropriate id, you can get the corresponding user's information ", tags={ "user" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "found user matching criteria", content = @Content(schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400", description = "bad input parameter") })
    @GetMapping(value = "/users/{id}",
            produces = { "application/json" })
    ResponseEntity<UserDto> findUser(@Parameter(in = ParameterIn.PATH, description = "user's id", required=true, schema=@Schema()) @PathVariable("id") UUID id);

}
