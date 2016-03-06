package edu.nju.git.comparators.usercomparators;

import edu.nju.git.VO.UserBriefVO;

import java.util.Comparator;

/**
 * the comparator compare <tt>UserBriefVo</tt> by its login, namely the login id of the user.
 */
public class UserNameComparator implements Comparator<UserBriefVO> {
    @Override
    public int compare(UserBriefVO o1, UserBriefVO o2) {
        return o1.getLogin().compareTo(o2.getLogin());
    }
}
