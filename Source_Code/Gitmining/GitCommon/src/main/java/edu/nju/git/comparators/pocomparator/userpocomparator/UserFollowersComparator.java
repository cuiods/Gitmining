package edu.nju.git.comparators.pocomparator.userpocomparator;

import edu.nju.git.PO.UserBriefPO;

import java.util.Comparator;

/**
 * this class is a comparator for {@link UserBriefPO}. it compares the user by its followers.<br>
 * the user who has more followers is larger than that with fewer followers.
 * @author benchaodong
 * @date 2016-03-05
 */
public class UserFollowersComparator implements Comparator<UserBriefPO> {
    @Override
    public int compare(UserBriefPO o1, UserBriefPO o2) {
        return o1.getFollowers() - o2.getFollowers();
    }
}
