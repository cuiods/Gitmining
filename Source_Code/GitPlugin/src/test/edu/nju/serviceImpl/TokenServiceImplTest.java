package edu.nju.serviceImpl;

import edu.nju.service.TokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by cuihao on 2016/8/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/applicationContext.xml"})
public class TokenServiceImplTest {

    @Resource
    private TokenService tokenService;

    @Test
    public void getApiToken() throws Exception {
        System.out.println(tokenService.getApiToken());
    }

}