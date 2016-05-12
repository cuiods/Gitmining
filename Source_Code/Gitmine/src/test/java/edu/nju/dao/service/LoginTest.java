package edu.nju.dao.service;

import edu.nju.entity.TblRegister;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * register dao test
 * @author cuihao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
public class LoginTest {
    @Resource
    RegisterDaoService registerDaoService;
    @Test
    public void testExistNameTrue() {
        boolean test = registerDaoService.existName("cuiods");
        Assert.assertEquals(true,test);
        boolean test2 = registerDaoService.existName("233");
        Assert.assertEquals(false,test2);
    }

    @Test
    public void testLogin(){
        boolean test = registerDaoService.login("cuiods","0628");
        Assert.assertTrue(test);
        boolean test2 = registerDaoService.login("cuiods","0000");
        Assert.assertTrue(!test2);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testRegister(){
        boolean test = registerDaoService.register("cuihao","0628","cuihaoemail@163.com");
        Assert.assertTrue(test);
    }
}