package edu.nju.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by cuihao on 2016/5/11.
 */
public class RepoLabelPK implements Serializable {
    private String repo;
    private String repoOwner;

    @Column(name = "repo")
    @Id
    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    @Column(name = "repo_owner")
    @Id
    public String getRepoOwner() {
        return repoOwner;
    }

    public void setRepoOwner(String repoOwner) {
        this.repoOwner = repoOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RepoLabelPK that = (RepoLabelPK) o;

        if (repo != null ? !repo.equals(that.repo) : that.repo != null) return false;
        if (repoOwner != null ? !repoOwner.equals(that.repoOwner) : that.repoOwner != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = repo != null ? repo.hashCode() : 0;
        result = 31 * result + (repoOwner != null ? repoOwner.hashCode() : 0);
        return result;
    }
}
