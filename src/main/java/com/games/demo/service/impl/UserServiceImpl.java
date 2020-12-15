package com.games.demo.service.impl;

import com.games.demo.dto.LoginUserDto;
import com.games.demo.dto.UserDto;
import com.games.demo.entity.Role;
import com.games.demo.entity.User;
import com.games.demo.exception.UserExistsException;
import com.games.demo.exception.WrongLoginException;
import com.games.demo.exception.WrongPasswordException;
import com.games.demo.repository.RoleRepository;
import com.games.demo.repository.UserRepository;
import com.games.demo.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(UserDto userDto)  throws UserExistsException{
        try {
            User user = findByEmail(userDto.getEmail());
        }
        catch (Exception e) {
            throw new UserExistsException("User with email "+userDto.getEmail()+" already exists.");
        }

        User user = userDto.ToUser();
        Role role = roleRepository.findByName("USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(role);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);

        User newUser = userRepository.save(user);
        return newUser;
    }

    @Override
    public User login(LoginUserDto userDto) throws WrongLoginException, WrongPasswordException {
        User user;
        try {
            user = findByEmail(userDto.getEmail());
        } catch(Exception e) {
            throw new WrongLoginException("Wrong login.");
        }

        if(!user.getPassword().equals(passwordEncoder.encode(userDto.getPassword())))
            throw new WrongPasswordException("Wrong password.");

        List<Role> userRoles = roleRepository.getUserRoles(user.getId());
        user.setRoles(userRoles);
        return user;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) throws Exception {
        return userRepository.findUserByEmail(email).get();
    }

    @Override
    public User findById(Integer id) throws Exception {
        return userRepository.findById(id).get();
    }

    @Override
    public boolean  isAdmin(Integer id) { return roleRepository.getUserRoles(id).contains(roleRepository.findByName("ADMIN"));}
}
