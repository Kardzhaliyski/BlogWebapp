package com.github.kardzhaliyski.blogwebapp.controllers;

import com.github.kardzhaliyski.blogwebapp.mappers.UserMapper;
import com.github.kardzhaliyski.blogwebapp.models.User;
import com.github.kardzhaliyski.blogwebapp.models.dto.LoginUserDTO;
import com.github.kardzhaliyski.blogwebapp.models.dto.RegisterUserDTO;
import com.github.kardzhaliyski.blogwebapp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AuthController(UserMapper userMapper, PasswordEncoder passwordEncoder, UserService userService) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register page";
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterUserDTO userDto) {
        if (!userDto.isValid()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Optional<User> userFromDB = userMapper.getByUsername(userDto.uname);
        if(userFromDB.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this username already exists!");
        }

        userService.registerUser(userDto);
    }
}
