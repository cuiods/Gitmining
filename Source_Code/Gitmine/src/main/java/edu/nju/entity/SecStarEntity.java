package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by Harry on 2016/5/22.
 */
@Entity
@Table(name = "sec_star", schema = "gitmining", catalog = "")
@IdClass(SecStarEntityPK.class)
public class SecStarEntity {
    private String repoOwner;
    private String repoName;
    private String stargazer;

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
    @Column(name = "stargazer")
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

        SecStarEntity that = (SecStarEntity) o;

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
