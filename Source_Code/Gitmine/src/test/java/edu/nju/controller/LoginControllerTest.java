package edu.nju.controller;

import edu.nju.model.service.HobbyModelService;
import edu.nju.model.service.LoginModelService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Mock
    private HobbyModelService hobbyModelService;

    @InjectMocks
    private LoginController loginController;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    public void register() throws Exception {
        when(loginModelImpl.register("harry14","12345","harry@126.com")).thenReturn(true);

        mockMvc.perform(get("/login/register").param("username","harry14").param("password","12345")
        .param("email","harry@126.com")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void login() throws Exception {
        String webUsername = "cuihao";
        String password = "12345";
        when(loginModelImpl.login(webUsername, password)).thenReturn(true);
        when(hobbyModelService.getStaredReponame(webUsername)).thenReturn(new HashSet<String>());
        when(hobbyModelService.getStaredUsername(webUsername)).thenReturn(new HashSet<String>());

        mockMvc.perform(post("/login/login").param("username", webUsername)
            .param("password", password)).andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void submitHobby() throws Exception {

    }

    @Test
    public void logout() throws Exception {
        mockMvc.perform(get("/login/logout")).andDo(print()).andExpect(status().isOk());
        mockMvc.perform(get("/login/logout").sessionAttr("webUsername","harry14")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getLoginName() throws Exception {
        mockMvc.perform(get("/login/name")).andExpect(status().isOk());
        mockMvc.perform(get("/login/name").sessionAttr("webUsername","harry14")).andExpect(status().isOk());

    }

}