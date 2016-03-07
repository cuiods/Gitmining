package edu.nju.git.tools;

import edu.nju.git.comparators.repocomparators.*;
import edu.nju.git.comparators.usercomparators.*;
import edu.nju.git.type.SortType;

import java.util.Comparator;

/**
 * this is a tool class for generating comparator by {}
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
            case CONTRI_NUM:return new RepoContriComparator();
            case UPDATE_TIME:return new RepoUpdateComparator();

            default:return null;
        }
    }
}
