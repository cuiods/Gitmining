package edu.nju.entity;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by cuihao on 2016/5/4.
 */
public class UserInfo {
    private Integer userId;

    private String userName;

    private String userSex;

    private String[] userXq;


    private Date userTime;

    public String[] getUserXq() {
        return userXq;
    }

    public void setUserXq(String[] userXq) {
        this.userXq = userXq;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }



    @Override
    public String toString() {
        return "UserInfo [userId=" + userId + ", userName=" + userName
                + ", userSex=" + userSex + ", userXq="
                + Arrays.toString(userXq) + ", userTime=" + userTime + "]";
    }

    public Date getUserTime() {
        return userTime;
    }

    public void setUserTime(Date userTime) {
        this.userTime = userTime;
    }
}
