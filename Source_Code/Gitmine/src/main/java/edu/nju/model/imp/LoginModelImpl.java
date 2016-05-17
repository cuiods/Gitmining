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
        if (registerDaoImpl.existName(webUsername)){
            return false;
        }
        registerDaoImpl.register(webUsername, password, email);
        return true;
    }

    @Override
    public boolean login(String webUsername, String password) {
        return registerDaoImpl.login(webUsername, password);
    }


    public RegisterDaoService getRegisterDaoImpl() {
        return registerDaoImpl;
    }

    public void setRegisterDaoImpl(RegisterDaoService registerDaoImpl) {
        this.registerDaoImpl = registerDaoImpl;
    }
}
