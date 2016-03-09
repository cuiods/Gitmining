package edu.nju.git.comparators.pocomparator.repopocomparator;

import edu.nju.git.PO.RepoBriefPO;

import java.util.Comparator;

/**
 * this is a comparator for {@link RepoBriefPO} that compares the repo by the time the repo is last <br>
 * updated.
 * @author benchaodong
 * @date 2016-03-06
 */
public class RepoUpdateComparator implements Comparator<RepoBriefPO>{
    @Override
    public int compare(RepoBriefPO o1, RepoBriefPO o2) {
        return o1.getLastUpdate().compareTo(o2.getLastUpdate());
    }
}
