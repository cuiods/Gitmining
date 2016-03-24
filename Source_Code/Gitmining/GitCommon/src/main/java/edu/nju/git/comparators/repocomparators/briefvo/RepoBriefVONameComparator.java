package edu.nju.git.comparators.repocomparators.briefvo;

import edu.nju.git.VO.RepoBriefVO;

import java.util.Comparator;

/**
 * this class is a comparator for {@link RepoBriefVO}. it compares the repo by its name
 * @author benchaodong
 * @date 2016-03-06
 */
public class RepoBriefVONameComparator implements Comparator<RepoBriefVO> {
    @Override
    public int compare(RepoBriefVO o1, RepoBriefVO o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
