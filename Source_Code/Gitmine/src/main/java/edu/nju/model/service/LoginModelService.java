package edu.nju.model.service;

/**
 * Created by Harry on 2016/5/14.
 * model interface for login and login
 */
public interface LoginModelService {

    public boolean register(String webUsername, String password, String email);

    public boolean login(String webUsername, String password);

    public boolean existUser(String webUsername, String email);

    public boolean existName(String webUsername);
}
