package edu.nju.controller;

import edu.nju.model.service.LoginModelService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Harry on 2016/5/14.
 */
public class LoginControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LoginModelService loginModelImpl;

    @InjectMocks
    private LoginController loginController;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    public void register() throws Exception {

    }

    @Test
    public void login() throws Exception {
        String webUsername = "cuihao";
        String password = "12345";
        when(loginModelImpl.login(webUsername, password)).thenReturn(true);
        when(loginModelImpl.existName(webUsername)).thenReturn(true);

        mockMvc.perform(post("/login").param("username", webUsername)
            .param("password", password)).andDo(print()).andExpect(status().isOk());

    }


    @Test
    public void selectHobby() throws Exception {

    }

    @Test
    public void submitHobby() throws Exception {

    }

}