package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by Harry on 2016/5/22.
 */
@Entity
@Table(name = "sec_subscriber", schema = "gitmining", catalog = "")
@IdClass(SecSubscriberEntityPK.class)
public class SecSubscriberEntity {
    private String repoOwner;
    private String repoName;
    private String subscriber;

    @Id
    @Column(name = "repo_owner")
    public String getRepoOwner() {
        return repoOwner;
    }

    public void setRepoOwner(String repoOwner) {
        this.repoOwner = repoOwner;
    }

    @Id
    @Column(name = "repo_name")
    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    @Id
    @Column(name = "subscriber")
    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SecSubscriberEntity that = (SecSubscriberEntity) o;

        if (repoOwner != null ? !repoOwner.equals(that.repoOwner) : that.repoOwner != null) return false;
        if (repoName != null ? !repoName.equals(that.repoName) : that.repoName != null) return false;
        if (subscriber != null ? !subscriber.equals(that.subscriber) : that.subscriber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = repoOwner != null ? repoOwner.hashCode() : 0;
        result = 31 * result + (repoName != null ? repoName.hashCode() : 0);
        result = 31 * result + (subscriber != null ? subscriber.hashCode() : 0);
        return result;
    }
}
