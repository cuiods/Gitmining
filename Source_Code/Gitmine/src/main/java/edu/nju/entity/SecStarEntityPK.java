package edu.nju.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Harry on 2016/5/22.
 */
public class SecStarEntityPK implements Serializable {
    private String repoOwner;
    private String repoName;
    private String stargazer;

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

    @Column(name = "stargazer")
    @Id
    public String getStargazer() {
        return stargazer;
    }

    public void setStargazer(String stargazer) {
        this.stargazer = stargazer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SecStarEntityPK that = (SecStarEntityPK) o;

        if (repoOwner != null ? !repoOwner.equals(that.repoOwner) : that.repoOwner != null) return false;
        if (repoName != null ? !repoName.equals(that.repoName) : that.repoName != null) return false;
        if (stargazer != null ? !stargazer.equals(that.stargazer) : that.stargazer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = repoOwner != null ? repoOwner.hashCode() : 0;
        result = 31 * result + (repoName != null ? repoName.hashCode() : 0);
        result = 31 * result + (stargazer != null ? stargazer.hashCode() : 0);
        return result;
    }
}
