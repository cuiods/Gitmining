package edu.nju.controller;

import edu.nju.common.Const;
import edu.nju.common.SortType;
import edu.nju.entity.TblRepo;
import edu.nju.model.imp.RepoModelImpl;
import edu.nju.model.pojo.RepoVO;
import edu.nju.model.service.RepoModelService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Harry on 2016/5/12.
 * test for RepoController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
public class RepoControllerTest {


    private MockMvc mockMvc;

    private RepoVO mockRepo;

    private List<RepoVO> mockRepoList;

//    @Mock
//    private RepoModelService repoModelService;

    @Resource
    private RepoController repoController;

    @Before
    public void setup(){
//        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(repoController).build();
    }


    public RepoControllerTest() {
        mockRepo = new RepoVO();
        mockRepo.setReponame("test_repo");
        mockRepo.setOwnerName("dbc");

        mockRepoList = new ArrayList<>();
        mockRepoList.add(mockRepo);

    }

    @Test
    public void recommend() throws Exception {
        mockMvc.perform(get("/repo/recommend")).andDo(print()).andExpect(status().isOk());
        //mockMvc.perform(get("/repo/recommend").sessionAttr("webUsername","harry14")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getRepos() throws Exception{

    }

    @Test
    public void list() throws Exception {
        mockMvc.perform(get("/repo/list?pageNum=1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getRepoInfo() throws Exception {
        mockMvc.perform(get("/repo/lepture/yue.css/basic")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void star() throws Exception {
        mockMvc.perform(post("/repo/star?ownername=danboy&reponame=10plateABC").sessionAttr("webUsername","harry")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void unStar() throws Exception {

    }

    @Test
    public void getRepoCommitByContributor() throws Exception {

    }

    @Test
    public void getRepoCodeFrequency() throws Exception {

    }

    @Test
    public void getRepoCommitByTime() throws Exception {

    }

    @Test
    public void statRepoCreateTime() throws Exception {

    }

    @Test
    public void statRepoSize() throws Exception {

    }

    @Test
    public void statRepoLanguage() throws Exception {

    }

    @Test
    public void statRepoStar() throws Exception {

    }

    @Test
    public void statRepoFork() throws Exception {

    }

    @Test
    public void getSearchResult() throws Exception {
        String keyword = "ru";
        String sortType = "star";
        String filterType = "";
        String language = "";
        String year = "";
        int pageNum = 1;
        boolean reverse = true;

//        when(repoModelService.getSearchResult(keyword,sortType,filterType,
//                language,year,pageNum,reverse)).thenReturn(mockRepoList);

        mockMvc.perform(post("/repo/search")
                        .param("keyword", "mo")
                        .param("sortType", "star")
                        .param("filterType", "")
                        .param("language", "")
                        .param("createYear", "")
                        .param("pageNum", "1")
                        .param("reverse", "true")
                        .param("isKeyChanged","true")).andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void getRepoBasicInfo() throws Exception {
//        when(repoModelService.getRepoBasicInfo("rubinius", "rubinius")).thenReturn(mockRepo);

        mockMvc.perform(get("/repo/mojombo/grit")).andDo(print()).andExpect(status().isOk());
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