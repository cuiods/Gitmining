package edu.nju.git.comparators.repocomparators.briefvo;

import edu.nju.git.VO.RepoBriefVO;

import java.util.Comparator;

/**
 * this is a comparator for {@link RepoBriefVO} that compares the repo by its star number.
 * @author benchaodong
 * @date 2016-03-06
 */
public class RepoBriefVOStarComparator implements Comparator<RepoBriefVO> {
    @Override
    public int compare(RepoBriefVO o1, RepoBriefVO o2) {
        return o1.getNum_stars()-o2.getNum_stars();
    }
}
