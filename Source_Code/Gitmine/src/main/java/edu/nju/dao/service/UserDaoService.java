package edu.nju.dao.service;

import edu.nju.entity.TblUser;

/**
 * Created by cuihao on 2016/5/4.
 */
public interface UserDaoService {
    /**
     * get user info
     * @param loginName
     * @return user info entity
     */
    public TblUser findUserByLoginName(String loginName);
}
