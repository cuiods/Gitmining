package edu.nju.dao.service;

import edu.nju.entity.TblRegister;

/**
 * login and register
 */
public interface LoginDaoService {
    /**
     * used in register
     * @param name
     * @return whether exist the user
     */
    public boolean existName(String name);

    /**
     * login
     * @param userName
     * @param password
     * @return TblRegister
     */
    public TblRegister login(String userName, String password);

    /**
     * registe
     * @param userName
     * @param passWord
     * @return isSucceed
     */
    public boolean register(String userName, String passWord);
}
