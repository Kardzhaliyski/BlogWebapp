package com.github.kardzhaliyski.blogwebapp.controllers;

import com.github.kardzhaliyski.blogwebapp.mappers.UserMapper;
import com.github.kardzhaliyski.blogwebapp.models.User;
import com.github.kardzhaliyski.blogwebapp.security.AuthTokenMapper;
import com.github.kardzhaliyski.blogwebapp.security.AuthenticationService;
import com.github.kardzhaliyski.blogwebapp.security.SecurityConfiguration;
import com.github.kardzhaliyski.blogwebapp.services.LoginService;
import com.github.kardzhaliyski.blogwebapp.services.UserService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@TestConfiguration
@Import(SecurityConfiguration.class)
public class TestConfig {
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private AuthTokenMapper authTokenMapper;

    @Bean
    public UserService userService(PasswordEncoder passwordEncoder) {
        return new UserService(passwordEncoder, userMapper);
    }

    @Bean
    public AuthenticationService authService() {
        return new AuthenticationService(authTokenMapper);
    }

    @Bean
    public LoginService loginService(AuthenticationService authenticationService, PasswordEncoder passwordEncoder){
        return new LoginService(userMapper, authenticationService , passwordEncoder);
    }

    @Qualifier("password")
    @Bean
    public String password() {
        return "Password";
    }

    @Bean
    public User user(PasswordEncoder passwordEncoder) {
        User user = new User();
        user.username = "User";
        user.password = passwordEncoder.encode(password());
        Mockito.when(userMapper.getByUsername(user.username))
                .thenReturn(Optional.of(user));
        return user;
    }
}
