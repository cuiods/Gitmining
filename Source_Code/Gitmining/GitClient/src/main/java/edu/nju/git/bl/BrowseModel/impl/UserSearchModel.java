package edu.nju.git.bl.BrowseModel.impl;

import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.bl.BrowseModel.service.UserBrowseModelService;
import edu.nju.git.bl.impl.UserBlImpl;
import edu.nju.git.exception.PageOutOfBoundException;
import edu.nju.git.tools.ComparatorFactory;
import edu.nju.git.type.SortType;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Harry on 2016/3/6.
 */
public class UserSearchModel implements UserBrowseModelService {

    private UserBlImpl userBl;

    public UserSearchModel(UserBlImpl userBl){
        this.userBl = userBl;
    }


    @Override
    public List<UserBriefVO> getSearchResult(String regex) {
        return null;
    }

    @Override
    public List<UserBriefVO> jumpToPage(int pageNum) throws PageOutOfBoundException {
        int totalPage = userBl.getTotalPage();
        if ((pageNum<1)||(pageNum>totalPage)){
            throw new PageOutOfBoundException("the page is out of bound");
        }
        List<UserBriefVO> briefUserList = userBl.getBriefUserList();
        int pageCapacity = userBl.getDEFAULT_PAGE_CAPACITY();
        //don't forget to change the value of current page
        userBl.setCurrentPage(pageNum);

        if (pageNum == totalPage) {
            return briefUserList.subList((pageNum-1)*pageCapacity, briefUserList.size());
        }

        return briefUserList.subList(pageCapacity*(pageNum-1), pageCapacity*pageNum);
    }

    @Override
    public List<UserBriefVO> nextPage() throws PageOutOfBoundException {
        return jumpToPage(userBl.getCurrentPage()+1);
    }

    @Override
    public List<UserBriefVO> previousPage() throws PageOutOfBoundException {
        return jumpToPage(userBl.getCurrentPage()-1);
    }

    @Override
    public List<UserBriefVO> sort(SortType sortType, boolean reverse ) {
        if (userBl.getTotalPage()<=0){
            return userBl.getBriefUserList();
        }
        Comparator c = ComparatorFactory.getcComparator(sortType);
        if (reverse){
            c=c.reversed();
        }
        Collections.sort(userBl.getBriefUserList(), c);
        List<UserBriefVO> theList = null;
        try{
            theList = jumpToPage(1);
        } catch (PageOutOfBoundException e) {
            e.printStackTrace();
        }
        return theList;
    }
}
