package edu.nju.git.datavisitors.uservisitors;

import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.data.service.UserDataService;
import edu.nju.git.type.SortType;

import java.util.List;

/**
 * Created by Harry on 2016/3/31.
 */
public class UserFollowingOrderVisitor extends SimpleUserVisitor {

    public UserFollowingOrderVisitor(int page, boolean reverse) {
        super(page, reverse);
    }

    @Override
    public List<UserBriefVO> visit(UserDataService userDataService) {
        return super.visit(userDataService, SortType.FOLLOWING_NUM);
    }
}
