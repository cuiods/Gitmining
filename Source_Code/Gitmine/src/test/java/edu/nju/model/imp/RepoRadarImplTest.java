package edu.nju.model.imp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Harry on 2016/5/16.
 * test repo radar
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
public class RepoRadarImplTest {

    @Resource
    private RepoRadarImpl repoRadarImpl;

    @Test
    public void getRadarSize() throws Exception {
        double size = repoRadarImpl.getRadarSize("rubinius","rubinius");
        assertTrue(size>0);
        System.out.println("rubinius radar size is: "+size);

        double hh = repoRadarImpl.getRadarSize("hhh","hhh");
        assertTrue(hh==0);
    }

    @Test
    public void getRadarFork() throws Exception {
        double fork  = repoRadarImpl.getRadarFork("rubinius", "rubinius");
        assertTrue(fork>=0);
        System.out.println("rubinius radar fork is: "+fork);

        double hh = repoRadarImpl.getRadarFork("hhh","hhh");
        assertTrue(hh==0);
    }

    @Test
    public void getRadarPopular() throws Exception {
        double popular = repoRadarImpl.getRadarPopular("rubinius", "rubinius");
        assertTrue(popular>0);
        System.out.println("rubinius radar popular is: "+popular);

        double hh = repoRadarImpl.getRadarPopular("hhh","hhh");
        assertTrue(hh==0);
    }

    @Test
    public void getRadarComplex() throws Exception {
        double complex = repoRadarImpl.getRadarComplex("rubinius", "rubinius");
        assertTrue(complex>0);
        System.out.println("rubinius radar complex is: "+complex);

        double hh = repoRadarImpl.getRadarComplex("hhh","hhh");
        System.out.println("hhh radar complex is: "+hh);
        assertTrue(hh==0);
    }

    @Test
    public void getRadarActive() throws Exception {
        double active = repoRadarImpl.getRadarActive("rubinius","rubinius");
        assertTrue(active == 1.0);
    }

}