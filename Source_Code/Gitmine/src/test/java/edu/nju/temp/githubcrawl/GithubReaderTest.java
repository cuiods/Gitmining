package edu.nju.temp.githubcrawl;

import com.fasterxml.jackson.databind.JsonNode;
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
public class GithubReaderTest {

    @Resource
    private GithubReader reader;

    @Test
    public void traverseUsers() throws Exception {

    }

    @Test
    public void getArrayNode() throws Exception {

    }

    @Test
    public void getSimpleNode() throws Exception {
        JsonNode node = reader.getSimpleNode("https://api.github.com/users/mojombo");
        assertNotNull(node);
        assertTrue(node.size()>0);
        assertTrue(node.get("login").asText().equals("mojombo"));
        System.out.println("mojombo company: "+node.get("company").asText());
    }

}