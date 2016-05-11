package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2016/5/11.
 */
@Entity
@Table(name = "tbl_subscriber", schema = "gitmining", catalog = "")
public class TblSubscriber {
    private String repo;
    private String repoOwner;
    private String subscriber;
    private long repoSubscriberId;

    @Basic
    @Column(name = "repo")
    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    @Basic
    @Column(name = "repo_owner")
    public String getRepoOwner() {
        return repoOwner;
    }

    public void setRepoOwner(String repoOwner) {
        this.repoOwner = repoOwner;
    }

    @Basic
    @Column(name = "subscriber")
    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    @Id
    @Column(name = "repo_subscriber_id")
    public long getRepoSubscriberId() {
        return repoSubscriberId;
    }

    public void setRepoSubscriberId(long repoSubscriberId) {
        this.repoSubscriberId = repoSubscriberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblSubscriber that = (TblSubscriber) o;

        if (repoSubscriberId != that.repoSubscriberId) return false;
        if (repo != null ? !repo.equals(that.repo) : that.repo != null) return false;
        if (repoOwner != null ? !repoOwner.equals(that.repoOwner) : that.repoOwner != null) return false;
        if (subscriber != null ? !subscriber.equals(that.subscriber) : that.subscriber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = repo != null ? repo.hashCode() : 0;
        result = 31 * result + (repoOwner != null ? repoOwner.hashCode() : 0);
        result = 31 * result + (subscriber != null ? subscriber.hashCode() : 0);
        result = 31 * result + (int) (repoSubscriberId ^ (repoSubscriberId >>> 32));
        return result;
    }
}
