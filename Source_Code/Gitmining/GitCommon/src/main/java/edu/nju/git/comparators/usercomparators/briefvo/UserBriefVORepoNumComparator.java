package edu.nju.git.comparators.usercomparators.briefvo;

import edu.nju.git.VO.UserBriefVO;

import java.util.Comparator;

/**
 * this class is a comparator for {@link UserBriefVO}. it compares the user by its repository number.<br>
 * the user who has more repositories is larger than that with fewer repositories.
 * @author benchaodong
 * @date 2016-03-05
 */
public class UserBriefVORepoNumComparator implements Comparator<UserBriefVO> {
    @Override
    public int compare(UserBriefVO o1, UserBriefVO o2) {
        return o1.getPublic_repos() - o2.getPublic_repos();
    }
}
