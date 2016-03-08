package edu.nju.git.tools;

import edu.nju.git.datavisitors.repovisitors.*;
import edu.nju.git.datavisitors.uservisitors.UserFollowerOrderVisitor;
import edu.nju.git.datavisitors.uservisitors.UserNameOrderVisitor;
import edu.nju.git.datavisitors.uservisitors.UserReposOrderVisitor;
import edu.nju.git.datavisitors.uservisitors.UserVisitor;
import edu.nju.git.type.SortType;

/**
 * This is a factory creating visitor
 * @author benchaodong
 * @date 2016-03-08
 */
public class VisitorFactory {

    public static UserVisitor getUserVisitor(SortType type) {
        switch (type) {
            case USER_NAME:return new UserNameOrderVisitor(1, false);
            case FOLLOWER_NUM:return new UserFollowerOrderVisitor(1, false);
            case REPO_NUM:return new UserReposOrderVisitor(1, false);
            default:return null;
        }
    }

    public static RepoVisitor getRepoVisitor(SortType type) {
        switch (type) {
            case REPO_NAME:return new RepoNameOrderVisitor(1, false);
            case FORK_NUM:return new RepoForkOrderVisitor(1,false);
            case STAR_NUM:return new RepoStarOrderVisitor(1, false);
            case SUBSCR_NUM:return new RepoSubscrOrderVisitor(1, false);
            case UPDATE_TIME:return new RepoUpdateOrderVisitor(1, false);
            default:return null;
        }
    }
}
