package com.github.kardzhaliyski.blogwebapp.controllers;

import com.github.kardzhaliyski.blogwebapp.mappers.UserMapper;
import com.github.kardzhaliyski.blogwebapp.models.User;
import com.github.kardzhaliyski.blogwebapp.models.dto.LoginUserDTO;
import com.github.kardzhaliyski.blogwebapp.models.dto.RegisterUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static jakarta.servlet.http.HttpServletResponse.SC_CONFLICT;

@RestController
@RequestMapping("/auth")
public class AuthController {

    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public AuthController(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

//    @GetMapping("/login")
//    public String loginPage() {
//        return "Login page";
//    }
//
//    @PostMapping
//    public String login(@RequestBody LoginUserDTO userDTO) {
//
//    }

    @GetMapping("/register")
    public String registerPage() {
        return "register page";
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterUserDTO userDto) {
        if (!userDto.isValid()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Optional<User> userFromDB = userMapper.getByUsername(userDto.uname);
        if(userFromDB.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this username already exists!");
        }

        String password = passwordEncoder.encode(userDto.psw);
        User user = new User(userDto.uname, userDto.fName, userDto.lName, userDto.email, password);
        userMapper.insert(user);

        return "Successful registration";
    }
}
