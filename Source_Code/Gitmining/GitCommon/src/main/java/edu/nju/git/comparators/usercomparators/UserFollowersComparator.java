package edu.nju.git.comparators.usercomparators;

import edu.nju.git.VO.UserBriefVO;

import java.util.Comparator;

/**
 * this class is a comparator for {@link UserBriefVO}. it compares the user by its followers.<br>
 * the user who has more followers is larger than that with fewer followers.
 * @author benchaodong
 * @date 2016-03-05
 */
public class UserFollowersComparator implements Comparator<UserBriefVO> {
    @Override
    public int compare(UserBriefVO o1, UserBriefVO o2) {
        return o1.getFollowers() - o2.getFollowers();
    }
}
