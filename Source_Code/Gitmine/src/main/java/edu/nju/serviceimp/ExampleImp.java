package edu.nju.serviceimp;

import edu.nju.dao.impl.UserDaoImp;
import edu.nju.dao.service.UserDaoService;
import edu.nju.entity.TblUser;
import edu.nju.service.ExampleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by cuihao on 2016/5/8.
 */
@Service
public class ExampleImp implements ExampleService {
    @Resource
    UserDaoService userDaoImp;
    public TblUser findUserByName(String name) {
        return userDaoImp.findUserByLoginName(name);
    }
}
