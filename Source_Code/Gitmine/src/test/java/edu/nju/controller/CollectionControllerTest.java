package edu.nju.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Harry on 2016/6/1.
 */
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
    }

    @Test
    public void getFavoriteUsers() throws Exception {

    }

}