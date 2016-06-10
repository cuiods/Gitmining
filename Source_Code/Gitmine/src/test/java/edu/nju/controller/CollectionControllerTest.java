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
 * Created by Harry on 2016/6/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
public class CollectionControllerTest {
    private MockMvc mockMvc;

    @Resource
    private CollectionController controller;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getFavoriteRepos() throws Exception {
        mockMvc.perform(get("/favorite/repos").sessionAttr("webUsername","harry14"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getFavoriteUsers() throws Exception {
        mockMvc.perform(get("/favorite/users").sessionAttr("webUsername","harry14"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getStaredRepoPage() throws Exception {
        mockMvc.perform(get("/favorite/repoPage").sessionAttr("webUsername","harry14"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getStaredUserPage() throws Exception {
        mockMvc.perform(get("/favorite/userPage").sessionAttr("webUsername","harry14"))
                .andDo(print()).andExpect(status().isOk());
    }

}