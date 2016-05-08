package edu.nju.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by cuihao on 2016/5/8.
 */
@Entity
@Table(name = "tbl_commit", schema = "gitmining", catalog = "")
public class TblCommit {
    private long commitId;
    private String sha;
    private long committer;
    private Timestamp time;
    private Integer numAdd;
    private Integer numDelete;
    private Integer numChanged;
    private long repoId;

    @Id
    @Column(name = "commit_id")
    public long getCommitId() {
        return commitId;
    }

    public void setCommitId(long commitId) {
        this.commitId = commitId;
    }

    @Basic
    @Column(name = "sha")
    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    @Basic
    @Column(name = "committer")
    public long getCommitter() {
        return committer;
    }

    public void setCommitter(long committer) {
        this.committer = committer;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "num_add")
    public Integer getNumAdd() {
        return numAdd;
    }

    public void setNumAdd(Integer numAdd) {
        this.numAdd = numAdd;
    }

    @Basic
    @Column(name = "num_delete")
    public Integer getNumDelete() {
        return numDelete;
    }

    public void setNumDelete(Integer numDelete) {
        this.numDelete = numDelete;
    }

    @Basic
    @Column(name = "num_changed")
    public Integer getNumChanged() {
        return numChanged;
    }

    public void setNumChanged(Integer numChanged) {
        this.numChanged = numChanged;
    }

    @Basic
    @Column(name = "repo_id")
    public long getRepoId() {
        return repoId;
    }

    public void setRepoId(long repoId) {
        this.repoId = repoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblCommit tblCommit = (TblCommit) o;

        if (commitId != tblCommit.commitId) return false;
        if (committer != tblCommit.committer) return false;
        if (repoId != tblCommit.repoId) return false;
        if (sha != null ? !sha.equals(tblCommit.sha) : tblCommit.sha != null) return false;
        if (time != null ? !time.equals(tblCommit.time) : tblCommit.time != null) return false;
        if (numAdd != null ? !numAdd.equals(tblCommit.numAdd) : tblCommit.numAdd != null) return false;
        if (numDelete != null ? !numDelete.equals(tblCommit.numDelete) : tblCommit.numDelete != null) return false;
        if (numChanged != null ? !numChanged.equals(tblCommit.numChanged) : tblCommit.numChanged != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (commitId ^ (commitId >>> 32));
        result = 31 * result + (sha != null ? sha.hashCode() : 0);
        result = 31 * result + (int) (committer ^ (committer >>> 32));
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (numAdd != null ? numAdd.hashCode() : 0);
        result = 31 * result + (numDelete != null ? numDelete.hashCode() : 0);
        result = 31 * result + (numChanged != null ? numChanged.hashCode() : 0);
        result = 31 * result + (int) (repoId ^ (repoId >>> 32));
        return result;
    }
}
