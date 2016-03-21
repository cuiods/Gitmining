package edu.nju.git.comparators.pocomparator.userpocomparator;

import edu.nju.git.PO.UserBriefPO;

import java.util.Comparator;

/**
 * the comparator compare <tt>UserBriefPO</tt> by its login, namely the login id of the user.
 * @author benchaodong
 * @date 2016-03-05
 */
public class UserNameComparator implements Comparator<UserBriefPO> {
    @Override
    public int compare(UserBriefPO o1, UserBriefPO o2) {
        return o1.getLogin().compareTo(o2.getLogin());
    }
}
