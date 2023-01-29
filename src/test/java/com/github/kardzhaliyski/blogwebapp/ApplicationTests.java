package com.github.kardzhaliyski.blogwebapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testPasswordEncoder() {
        BCryptPasswordEncoder e = new BCryptPasswordEncoder();
        String p1 = e.encode("hello");
        System.out.println(p1);
        String p2 = e.encode("hello");
        System.out.println(p2);
        assertNotEquals(p1, p2);
        System.out.println(e.matches("hello", p1));
    }

}
