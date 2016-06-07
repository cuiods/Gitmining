package edu.nju.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Harry on 2016/6/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
public class UserControllerTest {

    private MockMvc mockMvc;

    @Resource
    private UserController userController;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void home() throws Exception {

    }

    @Test
    public void list() throws Exception {

    }

    @Test
    public void getSearchResult() throws Exception {

    }

    @Test
    public void getUserInfo() throws Exception {

    }

    @Test
    public void getUserContribute() throws Exception {

    }

    @Test
    public void getUserSubscribe() throws Exception {

    }

    @Test
    public void getRelationGraph() throws Exception {

    }

    @Test
    public void statUserType() throws Exception {

    }

    @Test
    public void statUserRepos() throws Exception {

    }

    @Test
    public void statUserGists() throws Exception {

    }

    @Test
    public void statUserFollower() throws Exception {

    }

    @Test
    public void statUserCreateTime() throws Exception {

    }

    @Test
    public void statUserEmail() throws Exception {

    }

    @Test
    public void statUserCompany() throws Exception {

    }

    @Test
    public void statUserDistribution() throws Exception {

    }

    @Test
    public void starUser() throws Exception {

    }

    @Test
    public void unstarUser() throws Exception {
        mockMvc.perform(get("/user/unstar?username=defunkt").sessionAttr("webUsername","hhhhhh"))
                .andDo(print()).andExpect(status().isOk());
    }

}