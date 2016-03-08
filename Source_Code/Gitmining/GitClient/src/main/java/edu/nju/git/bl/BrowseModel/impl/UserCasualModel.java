package edu.nju.git.bl.BrowseModel.impl;

import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.bl.BrowseModel.service.UserBrowseModelService;
import edu.nju.git.bl.impl.UserBlImpl;
import edu.nju.git.datavisitors.uservisitors.SimpleUserVisitor;
import edu.nju.git.datavisitors.uservisitors.UserNameOrderVisitor;
import edu.nju.git.exception.PageOutOfBoundException;
import edu.nju.git.tools.VisitorFactory;
import edu.nju.git.type.SortType;

import java.util.List;

/**
 * Created by Harry on 2016/3/6.
 */
public class UserCasualModel implements UserBrowseModelService {

    private UserBlImpl userBl;

    /**
     * the visitor indicates the way the result list is sorted by.it will change as the <tt>sort</tt><br>
     *     method is invoked.In default the visitor is a {@link UserNameOrderVisitor}
     */
    private SimpleUserVisitor visitor;

    public UserCasualModel(UserBlImpl userBl) {
        this.userBl = userBl;
        visitor = new UserNameOrderVisitor(1, false);
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
        visitor.setPage(pageNum);
        userBl.setCurrentPage(pageNum);
        return visitor.visit(userBl.getUserDataService());
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
        int current_page = visitor.getPage();
        visitor = (SimpleUserVisitor) VisitorFactory.getUserVisitor(sortType);
        visitor.setPage(current_page);
        visitor.setReverse(reverse);
        return visitor.visit(userBl.getUserDataService());
    }
}
