package org.GitClient;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.RepoVO;
import edu.nju.git.bl.impl.RepoBlImpl;
import edu.nju.git.bl.service.RepoBlService;
import edu.nju.git.exception.PageOutOfBoundException;
import edu.nju.git.rmi.RMIClientLauncher;
import edu.nju.git.type.MostType;
import edu.nju.git.type.SortType;
import junit.framework.TestCase;

import java.util.List;

/**
 * Created by Harry on 2016/4/16.
 */
public class RepoBlServiceTest extends TestCase {

    private RepoBlService bl;

    public RepoBlServiceTest(){
        RMIClientLauncher.initRMI();
        bl = RepoBlImpl.instance();
    }

    public void testGetSearchResult() throws Exception {
        List<RepoBriefVO> result = bl.getSearchResult("aa");
        for (RepoBriefVO vo : result) {
            System.out.print(vo.getName()+" ");
        }
        System.out.println();
        assertNotNull(result);
    }

    public void testJumpToPage() throws Exception {
        bl.getSearchResult("");
        List<RepoBriefVO> result = bl.jumpToPage(3);
        assertNotNull(result);
        System.out.println(result.size());
        bl.getSearchResult("aa");
        List<RepoBriefVO> result2 = result;
        try {
            result2 = bl.jumpToPage(10000);
        } catch (PageOutOfBoundException e) {
            //e.printStackTrace();
            System.out.println("10000 page out of bound");
        }
        assertSame(result, result2);
    }

    public void testNextPage() throws Exception {

    }

    public void testPreviousPage() throws Exception {

    }

    public void testSort() throws Exception {
        List<RepoBriefVO> list1 = bl.getSearchResult("");
        List<RepoBriefVO> list2 = bl.sort(SortType.STAR_NUM);
        assertEquals(list1.size(), list2.size());
     }

    public void testGetRepoBasicInfo() throws Exception {
        RepoVO vo = bl.getRepoBasicInfo("Lexikos", "AutoHotkey_L");
        assertNotNull(vo);
        System.out.println(vo.getName());
    }

    public void testGetRepoContributor() throws Exception {
        List<String> ss = bl.getRepoContributor("Lexikos", "AutoHotkey_L");
        assertTrue(ss.size()>=0);
    }

    public void testGetRepoCollaborator() throws Exception {
        List<String> ss = bl.getRepoCollaborator("Lexikos", "AutoHotkey_L");
        assertTrue(ss.size()>=0);
    }

    public void testGetShownRepoList() throws Exception {
        bl.getSearchResult("");
        List<RepoBriefVO> list1 = bl.getShownRepoList();
        bl.nextPage();
        bl.previousPage();
        List<RepoBriefVO> list2 = bl.getShownRepoList();
        assertEquals(list1.size(), list2.size());
        for (int i=0;i<list1.size();i++){
            assertTrue(list1.get(i).getName().equals(list2.get(i).getName()));
        }
    }

    public void testGetCurrentPage() throws Exception {
        int page = bl.getCurrentPage();
        assertTrue(page>=0);
    }

    public void testGetTotalPage() throws Exception {
        int page = bl.getTotalPage();
        assertTrue(page>=0);
    }

    public void testGetMostRank() throws Exception {
        String repo = bl.getMostRank(MostType.REPO_ACTIVITY);
        System.out.println(repo);
    }

}