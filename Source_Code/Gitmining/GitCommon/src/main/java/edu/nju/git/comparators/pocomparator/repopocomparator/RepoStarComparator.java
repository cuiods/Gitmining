package edu.nju.git.comparators.pocomparator.repopocomparator;

import edu.nju.git.PO.RepoBriefPO;

import java.util.Comparator;

/**
 * this is a comparator for {@link RepoBriefPO} that compares the repo by its star number.
 * @author benchaodong
 * @date 2016-03-06
 */
public class RepoStarComparator implements Comparator<RepoBriefPO> {
    @Override
    public int compare(RepoBriefPO o1, RepoBriefPO o2) {
        return o1.getNum_stars()-o2.getNum_stars();
    }
}
