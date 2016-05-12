package edu.nju.controller;

import edu.nju.entity.TblRepo;
import edu.nju.model.imp.RepoModelImpl;
import edu.nju.model.service.RepoModelService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Harry on 2016/5/12.
 * test for RepoController
 */
public class RepoControllerTest {

    private RepoModelService repoModelImpl;

    public RepoControllerTest() {
        this.repoModelImpl = new RepoModelImpl();
    }

    @Test
    public void home() throws Exception {

    }

    @Test
    public void getSearchResult() throws Exception {
        String keyword = "rubinius";
        String sortType = "star";
        String filterType = "";
        String language = "";
        String year = "";
        int pageNum = 1;
        boolean reverse = true;
        List<TblRepo> resultList = repoModelImpl.getSearchResult(keyword, sortType, filterType,
                language, year, pageNum, reverse);
        assertNotNull(resultList);
        for (TblRepo repo : resultList){
            System.out.println(repo.getName());
        }
    }

    @Test
    public void getRepoBasicInfo() throws Exception {

    }

    @Test
    public void getRepoDetailGraph() throws Exception {

    }

    @Test
    public void getRepoModelImpl() throws Exception {

    }

    @Test
    public void setRepoModelImpl() throws Exception {

    }

}