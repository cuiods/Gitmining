package edu.nju.git.comparators.usercomparators.po;

import edu.nju.git.PO.UserPO;

import java.util.Comparator;

/**
 * Created by Harry on 2016/3/24.
 */
public class UserPONameComparator implements Comparator<UserPO> {
    @Override
    public int compare(UserPO o1, UserPO o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
