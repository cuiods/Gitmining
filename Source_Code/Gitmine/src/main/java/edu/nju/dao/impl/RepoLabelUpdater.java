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
        if (StringUtils.containsIgnoreCase(description,"management"))
            repoLabel.setManagement(1.0);
        if (StringUtils.containsIgnoreCase(description,"linux"))
            repoLabel.setLinux(1.0);
        if (StringUtils.containsIgnoreCase(description,"windows"))
            repoLabel.setWindows(1.0);
        if (StringUtils.containsIgnoreCase(description,"interface"))
            repoLabel.setInterFace(1.0);
        if (StringUtils.containsIgnoreCase(description,"os"))
            repoLabel.setOs(1.0);
        if (StringUtils.containsIgnoreCase(description,"server"))
            repoLabel.setServer(1.0);
        if (StringUtils.containsIgnoreCase(description,"tool"))
            repoLabel.setTool(1.0);
        if (StringUtils.containsIgnoreCase(description,"plugin"))
            repoLabel.setPlugin(1.0);
        if (StringUtils.containsIgnoreCase(description,"json"))
            repoLabel.setJson(1.0);
        if (StringUtils.containsIgnoreCase(description,"library"))
            repoLabel.setLibrary(1.0);
        if (StringUtils.containsIgnoreCase(description,"ui"))
            repoLabel.setUi(1.0);
        if (StringUtils.containsIgnoreCase(description,"database"))
            repoLabel.setDataBase(1.0);

        session.saveOrUpdate(repoLabel);

        session.close();
    }

}
