package edu.nju.git.tools;

import edu.nju.git.comparators.repocomparators.*;
import edu.nju.git.comparators.usercomparators.*;
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
            case USER_NAME: return new UserNameComparator();
            case FOLLOWER_NUM:return new UserFollowersComparator();
            case REPO_NUM:return new UserRepoNumComparator();
            case REPO_NAME:return new RepoNameComparator();
            case STAR_NUM:return new RepoStarComparator();
            case FORK_NUM:return new RepoForkComparator();
            case SUBSCR_NUM:return new RepoSubscrComparator();
            case UPDATE_TIME:return new RepoUpdateComparator();

            default:return null;
        }
    }
}
