package edu.nju.git.datavisitors.uservisitors;

import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.data.service.UserDataService;
import edu.nju.git.type.SortType;

import java.util.List;

/**
 * Created by harry on 16-3-8.
 */
public class UserReposOrderVisitor extends SimpleUserVisitor {

    public UserReposOrderVisitor(int page) {
        this(page, true);
    }

    public UserReposOrderVisitor(int page, boolean reverse) {
        super(page, reverse);
    }

    @Override
    public List<UserBriefVO> visit(UserDataService userDataService) {
        return super.visit(userDataService, SortType.REPO_NUM);
    }
}
