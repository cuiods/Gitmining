package edu.nju.model.service;

import edu.nju.model.pojo.SimpleChart;

/**
 * Created by Harry on 2016/5/16.
 * statistics for user
 */
public interface UserStatsService {

    /**
     * normal user and organization proportion
     * @return
     */
    public SimpleChart statsUserType();

    /**
     * anlyze the count of repos a user has
     * @return
     */
    public SimpleChart statsUserRepo();

    /**
     * analyze the count of gists a user has
     * @return
     */
    public SimpleChart statsUserGist();

    /**
     * anlyze the count of followers of a user
     * @return
     */
    public SimpleChart statsUserFollower();


    /**
     * analyze the time that a user register for github
     * @return
     */
    public SimpleChart statsUserCreateTime();

    /**
     * analyze the register enail of all users
     * @return
     */
    public SimpleChart statsUserEmail();

    /**
     * analyze the company of all users
     * @return
     */
    public SimpleChart statsUserCompany();
}
