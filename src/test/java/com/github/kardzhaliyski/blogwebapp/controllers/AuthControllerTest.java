package com.github.kardzhaliyski.blogwebapp.controllers;

import com.github.kardzhaliyski.blogwebapp.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest(classes = Application.class)
//@AutoConfigureMockMvc
@WebMvcTest(AuthController.class)
@Import(TestConfig.class)
class AuthControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private User user;
    @Autowired
    @Qualifier("password")
    private  String password;

    @Test
    public void testLogin() throws Exception {
        String json = String.format(
                """
                        {
                            "uname":"%s",
                            "psw":"%s"
                        }""", user.username, password);
        mvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().exists(HttpHeaders.AUTHORIZATION));
    }

    @Test
    public void testLoginShouldReturn401ForInvalidCredentials() throws Exception {
        String json = String.format(
                """
                        {
                            "uname":"%s",
                            "psw":"%s"
                        }""", user.username, password + "1234");
        mvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testLoginShouldReturn400ForMissingCredential() throws Exception {
        String json = String.format(
                """
                        {
                            "uname":"%s",
                        }""", user.username);
        mvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testLoginShouldReturn400ForMissingBody() throws Exception {
        mvc.perform(post("/auth/login"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testLoginShouldReturn415ForMissingContentType() throws Exception {
        String json = String.format(
                """
                        {
                            "uname":"%s",
                            "psw":"%s"
                        }""", user.username, password);

        mvc.perform(post("/auth/login")
                        .content(json))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void testLoginShouldReturn415ForUnsupportedContentType() throws Exception {
        String json = String.format(
                """
                        {
                            "uname":"%s",
                            "psw":"%s"
                        }""", user.username, password);

        mvc.perform(post("/auth/login")
                        .content(json)
                        .contentType(MediaType.TEXT_PLAIN))
                .andDo(print())
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void testRegister() throws Exception {
        String json = """
                {
                     "uname":"user2",
                     "fName":"user2",
                     "lName":"user2",
                     "email":"user2@mail.com",
                     "psw":"1234",
                     "pswRepeat":"1234"
                 }""";

        mvc.perform(post("/auth/register")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testRegisterShouldReturn400ForMissingData() throws Exception {
        String json = """
                {
                     "uname":"user2",
                     "email":"user2@mail.com",
                     "psw":"1234",
                     "pswRepeat":"1234"
                 }""";

        mvc.perform(post("/auth/register")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testRegisterShouldReturn400ForDifferentPswAndPswRepeat() throws Exception {
        String json = """
                {
                     "uname":"user2",
                     "fName":"user2",
                     "lName":"user2",
                     "email":"user2@mail.com",
                     "psw":"1234",
                     "pswRepeat":"5555"
                 }""";

        mvc.perform(post("/auth/register")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testRegisterShouldReturn409IfUsernameIsAlreadyTaken() throws Exception {
        String json = String.format("""
                {
                     "uname":"%s",
                     "fName":"user2",
                     "lName":"user2",
                     "email":"user2@mail.com",
                     "psw":"1234",
                     "pswRepeat":"1234"
                 }""", user.username);

        mvc.perform(post("/auth/register")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }
}