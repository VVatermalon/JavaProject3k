package com.games.demo.service;

import com.games.demo.dto.LoginUserDto;
import com.games.demo.dto.UserDto;
import com.games.demo.exception.UserExistsException;
import com.games.demo.exception.WrongLoginException;
import com.games.demo.exception.WrongPasswordException;
import com.games.demo.entity.User;

import java.util.List;

public interface UserService {
    User login(LoginUserDto userDto) throws WrongPasswordException, WrongLoginException;
    User register(UserDto userDto) throws UserExistsException;
    List<User> getAll();
    User findByEmail(String email) throws Exception;
    User findById(Integer id) throws Exception;
    boolean isAdmin(Integer id);
}