package com.games.demo.controller;

import com.games.demo.dto.UserDto;
import com.games.demo.entity.User;
import com.games.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class RegisterController {
    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary= "User registration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("register")
    public ResponseEntity register(@Valid @RequestBody UserDto userDetails) {
        try {
            userService.register(userDetails);
        } catch(Exception e) {
            return new ResponseEntity("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
