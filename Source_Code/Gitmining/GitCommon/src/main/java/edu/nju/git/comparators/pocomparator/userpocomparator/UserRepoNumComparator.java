package edu.nju.git.comparators.pocomparator.userpocomparator;

import edu.nju.git.PO.UserBriefPO;

import java.util.Comparator;

/**
 * this class is a comparator for {@link UserBriefPO}. it compares the user by its repository number.<br>
 * the user who has more repositories is larger than that with fewer repositories.
 * @author benchaodong
 * @date 2016-03-05
 */
public class UserRepoNumComparator implements Comparator<UserBriefPO> {
    @Override
    public int compare(UserBriefPO o1, UserBriefPO o2) {
        return o1.getPublic_repos() - o2.getPublic_repos();
    }
}
