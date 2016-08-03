package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2016/8/3.
 */
@Entity
@Table(name = "sec_contributor", schema = "gitmining", catalog = "")
@IdClass(SecContributorEntityPK.class)
public class SecContributorEntity {
    private String repoOwner;
    private String repoName;
    private String contributor;
    private int contributions;

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
    @Column(name = "contributor")
    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    @Basic
    @Column(name = "contributions")
    public int getContributions() {
        return contributions;
    }

    public void setContributions(int contributions) {
        this.contributions = contributions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SecContributorEntity that = (SecContributorEntity) o;

        if (contributions != that.contributions) return false;
        if (repoOwner != null ? !repoOwner.equals(that.repoOwner) : that.repoOwner != null) return false;
        if (repoName != null ? !repoName.equals(that.repoName) : that.repoName != null) return false;
        if (contributor != null ? !contributor.equals(that.contributor) : that.contributor != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = repoOwner != null ? repoOwner.hashCode() : 0;
        result = 31 * result + (repoName != null ? repoName.hashCode() : 0);
        result = 31 * result + (contributor != null ? contributor.hashCode() : 0);
        result = 31 * result + contributions;
        return result;
    }
}
