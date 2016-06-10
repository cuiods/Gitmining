package edu.nju.model.imp;

import edu.nju.model.pojo.RepoVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Harry on 2016/6/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
public class HobbyModelImplTest {


    @Resource
    private HobbyModelImpl hobbyModel;

    @Test
    public void starRepo() throws Exception {
        hobbyModel.starRepo("mojombo","god","harry14");
    }

    @Test
    public void unstarRepo() throws Exception {
        //hobbyModel.unStarUser("mojombo","hhhhhh");
    }

    @Test
    public void getTotalRepoStarPage() throws Exception {
        hobbyModel.getTotalRepoStarPage("harry14");
    }

    @Test
    public void starUser() throws Exception {
        hobbyModel.starUser("defunkt","harry14");
    }

    @Test
    public void unStarUser() throws Exception {

    }

    @Test
    public void getStaredUsers() throws Exception {
        hobbyModel.getStaredUsers("harry14",1);
    }

    @Test
    public void getTotalUserStarPage() throws Exception {
        hobbyModel.getTotalUserStarPage("harry14");
    }

    @Test
    public void getStaredRepos() throws Exception {
        List<RepoVO> list = hobbyModel.getStaredRepos("harry14",2);
        for (RepoVO vo:list){
            System.out.println(vo.getReponame());
        }
    }

    @Test
    public void getStaredReponame() throws Exception {
        hobbyModel.getStaredReponame("harry14");
    }

    @Test
    public void getStaredUsername() throws Exception {
        hobbyModel.getStaredUsername("harry14");
    }

}