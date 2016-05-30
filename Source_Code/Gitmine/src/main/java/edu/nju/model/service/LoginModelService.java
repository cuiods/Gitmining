package edu.nju.model.service;

import edu.nju.entity.SecRegisterLabelEntity;

/**
 * Created by Harry on 2016/5/14.
 * model interface for login and login
 */
public interface LoginModelService {

    public boolean register(String webUsername, String password, String email);

    public boolean login(String webUsername, String password);

    /**
     * let web user init his hobby
     * @param labelEntity
     * @return
     */
    public boolean initHobby(SecRegisterLabelEntity labelEntity);

}
