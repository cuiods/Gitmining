package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2016/5/11.
 */
@Entity
@Table(name = "tbl_subscriber", schema = "gitmining", catalog = "")
public class TblSubscriber {
    private long repo;

    public void setRepo(String repo) {
        this.repo = repo;
    }

    private long subscriber;

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    private long repoSubscriberId;

    @Basic
    @Column(name = "repo")
    public long getRepo() {
        return repo;
    }

    public void setRepo(long repo) {
        this.repo = repo;
    }

    @Basic
    @Column(name = "subscriber")
    public long getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(long subscriber) {
        this.subscriber = subscriber;
    }

    @Id
    @Column(name = "repo_subscriber_id")
    public long getRepoSubscriberId() {
        return repoSubscriberId;
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

        if (repo != that.repo) return false;
        if (subscriber != that.subscriber) return false;
        if (repoSubscriberId != that.repoSubscriberId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (repo ^ (repo >>> 32));
        result = 31 * result + (int) (subscriber ^ (subscriber >>> 32));
        result = 31 * result + (int) (repoSubscriberId ^ (repoSubscriberId >>> 32));
        return result;
    }    private String repoOwner;

    @Basic
    @Column(name = "repo_owner")
    public String getRepoOwner() {
        return repoOwner;
    }

    public void setRepoOwner(String repoOwner) {
        this.repoOwner = repoOwner;
    }
}
