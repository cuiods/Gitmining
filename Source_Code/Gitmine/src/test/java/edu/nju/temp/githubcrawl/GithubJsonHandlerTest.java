package edu.nju.temp.githubcrawl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Harry on 2016/5/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
public class GithubJsonHandlerTest {
    @Resource
    private GithubJsonHandler handler;

    @Test
    public void loopNoRepoUsers() throws Exception {
        //handler.loopNoRepoUsers();
    }

    @Test
    public void trverse() throws Exception {
        //handler.trverse();
    }

    @Test
    public void addUser() throws Exception {
        //handler.addUser("Harry1001");
    }

    @Test
    public void addRepos() throws Exception {
        //handler.addRepos("https://api.github.com/users/mojombo/repos");
    }

    @Test
    public void addContributors() throws Exception {
        //handler.addContributors("mojombo","30daysoflaptops.github.io");
    }

    @Test
    public void addSubscriber() throws Exception {
        //handler.addSubscriber("mojombo","30daysoflaptops.github.io");
    }

    @Test
    public void addStargazer() throws Exception {
        //handler.addStargazer("mojombo","30daysoflaptops.github.io");
    }

}