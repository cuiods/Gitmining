package edu.nju.git.comparators.pocomparator.repopocomparator;

import edu.nju.git.PO.RepoBriefPO;

import java.util.Comparator;

/**
 * this class is a comparator for {@link RepoBriefPO}. it compares the repo by its name
 * @author benchaodong
 * @date 2016-03-06
 */
public class RepoNameComparator implements Comparator<RepoBriefPO> {
    @Override
    public int compare(RepoBriefPO o1, RepoBriefPO o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
