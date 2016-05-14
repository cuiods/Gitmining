package edu.nju.model.imp;

import edu.nju.dao.service.RegisterDaoService;
import edu.nju.model.service.LoginModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Harry on 2016/5/14.
 * this class is designed to handle login and register logic
 */
@Service
public class LoginModelImpl implements LoginModelService{

    @Resource
    private RegisterDaoService registerDaoImpl;

    public LoginModelImpl() {
    }

    @Override
    public boolean register(String webUsername, String password, String email) {
        return false;
    }

    @Override
    public boolean login(String webUsername, String password) {

        return true;
    }

    @Override
    public boolean existUser(String webUsername, String email) {
        return false;
    }

    @Override
    public boolean existName(String webUsername) {
        return false;
    }

    public RegisterDaoService getRegisterDaoImpl() {
        return registerDaoImpl;
    }

    public void setRegisterDaoImpl(RegisterDaoService registerDaoImpl) {
        this.registerDaoImpl = registerDaoImpl;
    }
}
