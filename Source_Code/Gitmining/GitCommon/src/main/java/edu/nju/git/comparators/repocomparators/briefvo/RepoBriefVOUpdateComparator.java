package edu.nju.git.comparators.repocomparators.briefvo;

import edu.nju.git.VO.RepoBriefVO;

import java.util.Comparator;

/**
 * this is a comparator for {@link RepoBriefVO} that compares the repo by the time the repo is last <br>
 * updated.
 * @author benchaodong
 * @date 2016-03-06
 */
public class RepoBriefVOUpdateComparator implements Comparator<RepoBriefVO>{
    @Override
    public int compare(RepoBriefVO o1, RepoBriefVO o2) {
        return o1.getLastUpdate().compareTo(o2.getLastUpdate());
    }
}
