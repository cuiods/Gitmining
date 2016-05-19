package edu.nju.dao.impl;

import edu.nju.entity.RepoLabel;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Harry on 2016/5/19.
 * this class is meant to save repo labels to the database table <b>repo_label</b>
 */
@Repository
public class RepoLabelUpdater {

    @Resource
    private SessionFactory sessionFactory;

    public void saveOrUpdateLabel(String ownername, String reponame, String description){
        Session session = sessionFactory.openSession();
        RepoLabel repoLabel = new RepoLabel();
        repoLabel.setRepoOwner(ownername);
        repoLabel.setRepo(reponame);
        if (StringUtils.containsIgnoreCase(description,"web"))
            repoLabel.setWeb(1.0);
        if (StringUtils.containsIgnoreCase(description,"app"))
            repoLabel.setApp(1.0);
        if (StringUtils.containsIgnoreCase(description,"api"))
            repoLabel.setApi(1.0);
        if (StringUtils.containsIgnoreCase(description,"framework"))
            repoLabel.setFramework(1.0);
        if (StringUtils.containsIgnoreCase(description,"cms"))
            repoLabel.setCms(1.0);
        if (StringUtils.containsIgnoreCase(description,"django"))
            repoLabel.setDjango(1.0);
        if (StringUtils.containsIgnoreCase(description,"emacs"))
            repoLabel.setEmacs(1.0);
        if (StringUtils.containsIgnoreCase(description,"web"))
            repoLabel.setWeb(1.0);


        session.close();
    }

}
