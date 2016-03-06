package edu.nju.git.bl.BrowseModel.impl;

import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.bl.BrowseModel.service.UserBrowseModelService;
import edu.nju.git.bl.impl.UserBlImpl;
import edu.nju.git.exception.PageOutOfBoundException;
import edu.nju.git.type.SortType;

import java.util.List;

/**
 * Created by Harry on 2016/3/6.
 */
public class UserSearchModel implements UserBrowseModelService {

    private static UserSearchModel uniqueInstance = null;

    public static UserSearchModel instance(){
        if (uniqueInstance == null) {
            uniqueInstance = new UserSearchModel(UserBlImpl.instance());
        }
        return uniqueInstance;
    }

    private UserBlImpl userBl;

    private UserSearchModel(UserBlImpl userBl){
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
            return briefUserList.subList(pageNum*pageCapacity, briefUserList.size());
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
    public List<UserBriefVO> sort(SortType sortType, boolean reverse) {
        return null;
    }
}
