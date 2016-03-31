package edu.nju.git.comparators.usercomparators.briefvo;

import edu.nju.git.VO.UserBriefVO;

import java.util.Comparator;

/**
 * Created by Harry on 2016/3/31.
 */
public class UserBriefVOFollowingComparator implements Comparator<UserBriefVO> {
    @Override
    public int compare(UserBriefVO o1, UserBriefVO o2) {
        return o1.getFollowing() - o2.getFollowing();
    }
}
