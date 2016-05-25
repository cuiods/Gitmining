package edu.nju.dao.impl;

import edu.nju.common.SortType;
import edu.nju.dao.service.SecRepoDaoService;
import edu.nju.entity.SecRepoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Harry on 2016/5/24.
 */
@Repository
public class SecDaoImpl implements SecRepoDaoService {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public List<SecRepoEntity> getSearchResult(String keyword, int offset, int maxNum, SortType type, boolean isDesc, String filterType, String language, String createYear) {
        Session session =sessionFactory.openSession();
        //Query query = session.createQuery("from SecRepoEntity where description like '%'+:filter+'%' and () ");

        session.close();


        return null;
    }

    @Override
    public List<SecRepoEntity> getRepos(SortType sortType, boolean isDesc, int offset, int maxNum) {
        return null;
    }

    @Override
    public SecRepoEntity getRepoBasicInfo(String owner, String repoName) {
        return null;
    }

    @Override
    public List<String> getRepoContributor(String owner, String repoName) {
        return null;
    }

    @Override
    public List<String> getRepoSubscriber(String owner, String repoName) {
        return null;
    }

    @Override
    public double getMaxRepoSize() {
        return 0;
    }

    @Override
    public double getMinRepoSize() {
        return 0;
    }

    @Override
    public double getMaxRepoFork() {
        return 0;
    }

    @Override
    public double getMinRepoFork() {
        return 0;
    }

    @Override
    public double getMaxRepoStar() {
        return 0;
    }

    @Override
    public double getMinRepoStar() {
        return 0;
    }

    @Override
    public double getMaxRepoContriCount() {
        return 0;
    }

    @Override
    public double getMinRepoContriCount() {
        return 0;
    }

    @Override
    public double getMaxCommitCount() {
        return 0;
    }

    @Override
    public double getMinCommitCount() {
        return 0;
    }

    @Override
    public double getRepoContriCount(String ownername, String reponame) {
        return 0;
    }

    @Override
    public double getRepoCommitCount(String ownername, String reponame) {
        return 0;
    }

    @Override
    public long getStatsCreateTime() {
        return 0;
    }

    @Override
    public long getStatsFork(int min, int max) {
        return 0;
    }

    @Override
    public long getStatsStar(int min, int max) {
        return 0;
    }

    @Override
    public List getStatsLanguage(int maxResults) {
        return null;
    }

    @Override
    public long getStatsSize(int min, int max) {
        return 0;
    }
}
