package com.bci.cl.user_ms.controller;

import com.bci.cl.user_ms.dto.UserOutDTO;
import com.bci.cl.user_ms.dto.UsersDTO;
import com.bci.cl.user_ms.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityScheme(type = SecuritySchemeType.HTTP, name = "bearerAuth",
        description = "authorization with JWT token", scheme = "bearer",
        bearerFormat = "JWT")
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService service;

    @Operation(
            operationId = "createUser",
            summary = "Create a new user",
            description = "This endpoint allows to create a new user. A valid Bearer token is required.",
            security = {@SecurityRequirement(name = "bearerAuth"),})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserOutDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Bearer token missing or invalid",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid data provided")
    })
    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody UsersDTO request) {
        return service.createUser(request);
    }

    @Operation(summary = "Get all users",
            description = "Fetches all registered users. A valid Bearer token is required.",
            security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of users retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsersDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Bearer token missing or invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Elements not found",
                    content = @Content)
    })
    @GetMapping("/all")
    public ResponseEntity<Object> getUsers(){
        return service.getUsers();
    }
}
