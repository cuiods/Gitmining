package edu.nju.git.PO;

import java.io.Serializable;

/**
 * This class is used in data layer to store <b>ALL</b> the indexes of user information.
 * <p>In order to save network expenditure, we save all the user's <b>brief</b> information<br>
 * to local disk in advance. Therefor when we search user information, we can refer to this class<br>
 * instead of database(Actually we want to user database but Professor Liu forbid us from using it.<br>
 * Holy Shit!)
 * @author benchaodong
 * @date 2016-03-04
 */
public class UserBriefPO implements Serializable{
    private String id;
    private String login;
    private int followNum = 0;
    private int followingNum = 0;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public int getFollowNum() {
        return followNum;
    }
    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }
    public int getFollowingNum() {
        return followingNum;
    }
    public void setFollowingNum(int followingNum) {
        this.followingNum = followingNum;
    }
}
