package edu.nju.git.comparators.usercomparators;

import edu.nju.git.VO.UserBriefVO;

import java.util.Comparator;

/**
 * Created by Harry on 2016/3/6.
 */
public class UserRepoNumComparator implements Comparator<UserBriefVO> {
    @Override
    public int compare(UserBriefVO o1, UserBriefVO o2) {
        return o1.getPublic_repos() - o2.getPublic_repos();
    }
}
