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
    public void home() throws Exception {
//        when(repoModelService.getPopularRepo()).thenReturn(mockRepoList);
//        when(repoModelService.getRepos(SortType.Repo_Name, false, 0, Const.ITEMS_PER_PAGE)).thenReturn(mockRepoList);

        mockMvc.perform(get("/repo/home")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getRepos() throws Exception{
        mockMvc.perform(get("/repo/list?pageNum=1")).andDo(print()).andExpect(status().isOk());
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

//        when(repoModelService.getSearchResult(keyword,sortType,filterType,
//                language,year,pageNum,reverse)).thenReturn(mockRepoList);

        mockMvc.perform(post("/repo/search")
                        .param("keyword", "rubinius")
                        .param("sortType", "star")
                        .param("filterType", "")
                        .param("language", "")
                        .param("createYear", "")
                        .param("pageNum", "1")
                        .param("reverse", "true")).andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void getRepoBasicInfo() throws Exception {
//        when(repoModelService.getRepoBasicInfo("rubinius", "rubinius")).thenReturn(mockRepo);

        mockMvc.perform(post("/repo/rubinius/rubinius")).andDo(print()).andExpect(status().isOk());
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