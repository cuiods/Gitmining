package edu.nju.dao.service;

import edu.nju.entity.RegisterLabel;
import edu.nju.entity.TblRegister;

/**
 * login and register
 */
public interface RegisterDaoService {
    /**
     * used in register
     * @param name
     * @return whether exist the user
     */
    public boolean existName(String name);

    /**
     * when register a new user, check that the email and username don't exist
     * @param username
     * @param email
     * @return
     */
    public boolean existUser(String username, String email);

    /**
     * login
     * @param userName
     * @param password
     * @return TblRegister
     */
    public boolean login(String userName, String password);

    /**
     * registe
     * @param userName
     * @param passWord
     * @return isSucceed
     */
    public boolean register(String userName, String passWord, String email);

    /**
     * get register labels
     * @param userName
     *      username of the register
     * @return
     *      registerLabel
     */
    public RegisterLabel getRegisterInterest(String userName);

    /**
     * save register labels
     * @param registerLabel
     * @return
     *      is succeed.
     */
    public boolean saveRegisterInterest(RegisterLabel registerLabel);
}
