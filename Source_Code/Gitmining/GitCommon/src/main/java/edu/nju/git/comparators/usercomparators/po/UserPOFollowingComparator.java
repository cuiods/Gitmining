package edu.nju.git.comparators.usercomparators.po;

import edu.nju.git.PO.UserPO;

import java.util.Comparator;

/**
 * Created by Harry on 2016/3/31.
 */
public class UserPOFollowingComparator implements Comparator<UserPO> {
    @Override
    public int compare(UserPO o1, UserPO o2) {
        return o1.getFollowingNum() - o2.getFollowingNum();
    }
}
