package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2016/5/10.
 */
@Entity
@Table(name = "tbl_subscriber", schema = "gitmining", catalog = "")
public class TblSubscriber {
    private long repo;
    private long subscriber;
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
    }
}
