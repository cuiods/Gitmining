package edu.nju.git.comparators.repocomparators;

import edu.nju.git.VO.RepoBriefVO;

import java.util.Comparator;

/**
 * this is a comparator for {@link RepoBriefVO} that compares the repo by the fork amount of the repo.
 * @author benchaodong
 * @date 2016-03-06
 */
public class RepoForkComparator implements Comparator<RepoBriefVO> {
    @Override
    public int compare(RepoBriefVO o1, RepoBriefVO o2) {
        return o1.getNum_forks()-o2.getNum_forks();
    }
}