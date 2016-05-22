package edu.nju.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Harry on 2016/5/22.
 */
public class SecSubscriberEntityPK implements Serializable {
    private String repoOwner;
    private String repoName;
    private String subscriber;

    @Column(name = "repo_owner")
    @Id
    public String getRepoOwner() {
        return repoOwner;
    }

    public void setRepoOwner(String repoOwner) {
        this.repoOwner = repoOwner;
    }

    @Column(name = "repo_name")
    @Id
    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    @Column(name = "subscriber")
    @Id
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

        SecSubscriberEntityPK that = (SecSubscriberEntityPK) o;

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
