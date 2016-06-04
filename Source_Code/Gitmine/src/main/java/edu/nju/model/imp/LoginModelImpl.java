package edu.nju.model.imp;

import edu.nju.dao.service.RegisterDaoService;
import edu.nju.entity.SecRegisterLabelEntity;
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
        if (registerDaoImpl.existUser(webUsername, email)){
            return false;
        }
        else {
            boolean result = registerDaoImpl.register(webUsername, password, email);
            if (result){
                SecRegisterLabelEntity labelEntity = new SecRegisterLabelEntity();
                labelEntity.setRegisterLogin(webUsername);
                registerDaoImpl.saveOrUpdateRegisterInterest(labelEntity);
            }
            return result;
        }

    }

    @Override
    public boolean login(String webUsername, String password) {
        return registerDaoImpl.login(webUsername, password);
    }

    @Override
    public boolean initHobby(SecRegisterLabelEntity labelEntity) {
        return registerDaoImpl.saveOrUpdateRegisterInterest(labelEntity);
    }
}
