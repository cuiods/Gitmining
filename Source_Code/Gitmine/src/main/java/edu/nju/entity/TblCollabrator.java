package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2016/5/11.
 */
@Entity
@Table(name = "tbl_collabrator", schema = "gitmining", catalog = "")
public class TblCollabrator {
    private String repo;
    private String repoOwner;
    private String collabrator;
    private long collabratorId;

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
    @Column(name = "collabrator")
    public String getCollabrator() {
        return collabrator;
    }

    public void setCollabrator(String collabrator) {
        this.collabrator = collabrator;
    }

    @Id
    @Column(name = "collabrator_id")
    public long getCollabratorId() {
        return collabratorId;
    }

    public void setCollabratorId(long collabratorId) {
        this.collabratorId = collabratorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblCollabrator that = (TblCollabrator) o;

        if (collabratorId != that.collabratorId) return false;
        if (repo != null ? !repo.equals(that.repo) : that.repo != null) return false;
        if (repoOwner != null ? !repoOwner.equals(that.repoOwner) : that.repoOwner != null) return false;
        if (collabrator != null ? !collabrator.equals(that.collabrator) : that.collabrator != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = repo != null ? repo.hashCode() : 0;
        result = 31 * result + (repoOwner != null ? repoOwner.hashCode() : 0);
        result = 31 * result + (collabrator != null ? collabrator.hashCode() : 0);
        result = 31 * result + (int) (collabratorId ^ (collabratorId >>> 32));
        return result;
    }
}
