package edu.nju.git.comparators.repocomparators.po;

import edu.nju.git.PO.RepoPO;

import java.util.Comparator;

/**
 * Created by Harry on 2016/3/24.
 */
public class RepoPOSubscrComparator implements Comparator<RepoPO> {
    @Override
    public int compare(RepoPO o1, RepoPO o2) {
        return o1.getNum_subscribers() - o2.getNum_subscribers();
    }
}
