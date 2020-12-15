package com.games.demo.controller;

import com.games.demo.dto.LoginUserDto;
import com.games.demo.entity.User;
import com.games.demo.exception.WrongLoginException;
import com.games.demo.security.jwt.JwtTokenProvider;
import com.games.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class LoginController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public LoginController(UserService userService, AuthenticationManager authenticationManager,
                           JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Operation(summary= "User authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User authorized", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("login")
    public ResponseEntity login(@RequestBody LoginUserDto userDto) throws WrongLoginException {
        try {
            String email = userDto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, userDto.getPassword()));
            User user = userService.findByEmail(email);

            if (user == null) {
                throw new WrongLoginException("User with login: " + email + " not found");
            }

            String token = jwtTokenProvider.createToken(email, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("email", user.getEmail());
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
        catch(Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }
}
