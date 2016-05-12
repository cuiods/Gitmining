package edu.nju.model.service;

import edu.nju.entity.TblUser;

/**
 * service of logic controller
 */
public interface ExampleService {
    public TblUser findUserByName(String name);
}
