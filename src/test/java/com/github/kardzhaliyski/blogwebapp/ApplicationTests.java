package com.github.kardzhaliyski.blogwebapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationTests {

    @Test
    void testPasswordEncoder() {
        BCryptPasswordEncoder e = new BCryptPasswordEncoder();
        String p1 = e.encode("hello");
        String p2 = e.encode("hello");
        assertNotEquals(p1, p2);
        assertTrue(e.matches("hello", p1));
    }





}
