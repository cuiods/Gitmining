package edu.nju.git.tools;

import edu.nju.git.comparators.repocomparators.briefvo.*;
import edu.nju.git.comparators.usercomparators.briefvo.UserBriefVOFollowersComparator;
import edu.nju.git.comparators.usercomparators.briefvo.UserBriefVOFollowingComparator;
import edu.nju.git.comparators.usercomparators.briefvo.UserBriefVONameComparator;
import edu.nju.git.comparators.usercomparators.briefvo.UserBriefVORepoNumComparator;
import edu.nju.git.type.SortType;

import java.util.Comparator;

/**
 * this is a tool class for generating comparator by {@link SortType}
 * @author benchaodong
 * @date 2016-03-06
 */
public class ComparatorFactory {
    public static Comparator getcComparator(SortType type){
        switch (type) {
            case USER_NAME: return new UserBriefVONameComparator();
            case FOLLOWER_NUM:return new UserBriefVOFollowersComparator();
            case REPO_NUM:return new UserBriefVORepoNumComparator();
            case FOLLOWING_NUM:return new UserBriefVOFollowingComparator();

            case REPO_NAME:return new RepoBriefVONameComparator();
            case STAR_NUM:return new RepoBriefVOStarComparator();
            case FORK_NUM:return new RepoBriefVOForkComparator();
            case SUBSCR_NUM:return new RepoBriefVOSubscrComparator();
            case UPDATE_TIME:return new RepoBriefVOUpdateComparator();

            default:return null;
        }
    }
}
