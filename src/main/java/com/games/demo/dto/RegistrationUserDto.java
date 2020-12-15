package com.games.demo.dto;

import com.games.demo.entity.User;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegistrationUserDto {

    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Wrong email address")
    private String email;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 20, message = "Password must be equal or greater than 6 characters and less than 20 characters")
    private String password;

    @NotNull(message = "Nickname cannot be null")
    @NotBlank(message = "Nickname cannot be empty")
    @Size(min = 1, max = 15, message = "Nickname must be equal or greater than 1 characters and less than 15 characters")
    private String nickname;

    public User ToUser(){
        User user = new User();
        user.setEmail(this.getEmail());
        user.setPassword(this.getPassword());
        user.setNickname(this.getNickname());
        return user;
    }
}