package edu.nju.model.service;

import edu.nju.model.pojo.RelationVO;

import java.util.List;

/**
 * Created by darxan on 2016/6/1.
 */
public interface RelationsModelService {
    /**
     * get the repos and users having relations with  {username}
     * @param username
     * @return
     */
    public List<RelationVO> getRelationsByUser(String username);

    /**
     *  get the repos and users having relations with  {ownername}/{reponame}
     * @param ownername
     * @param reponame
     * @return
     */
    public List<RelationVO> getRelationsByRepo(String ownername, String reponame);
}
