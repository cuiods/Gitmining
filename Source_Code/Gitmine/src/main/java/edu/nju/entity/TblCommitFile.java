package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2016/5/11.
 */
@Entity
@Table(name = "tbl_commit_file", schema = "gitmining", catalog = "")
public class TblCommitFile {
    private long commitId;
    private String commitFile;
    private Integer numAdd;
    private Integer numDelete;
    private Integer numChanged;
    private int commitFileId;

    @Basic
    @Column(name = "commit_id")
    public long getCommitId() {
        return commitId;
    }

    public void setCommitId(long commitId) {
        this.commitId = commitId;
    }

    @Basic
    @Column(name = "commit_file")
    public String getCommitFile() {
        return commitFile;
    }

    public void setCommitFile(String commitFile) {
        this.commitFile = commitFile;
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

    @Id
    @Column(name = "commit_file_id")
    public int getCommitFileId() {
        return commitFileId;
    }

    public void setCommitFileId(int commitFileId) {
        this.commitFileId = commitFileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblCommitFile that = (TblCommitFile) o;

        if (commitId != that.commitId) return false;
        if (commitFileId != that.commitFileId) return false;
        if (commitFile != null ? !commitFile.equals(that.commitFile) : that.commitFile != null) return false;
        if (numAdd != null ? !numAdd.equals(that.numAdd) : that.numAdd != null) return false;
        if (numDelete != null ? !numDelete.equals(that.numDelete) : that.numDelete != null) return false;
        if (numChanged != null ? !numChanged.equals(that.numChanged) : that.numChanged != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (commitId ^ (commitId >>> 32));
        result = 31 * result + (commitFile != null ? commitFile.hashCode() : 0);
        result = 31 * result + (numAdd != null ? numAdd.hashCode() : 0);
        result = 31 * result + (numDelete != null ? numDelete.hashCode() : 0);
        result = 31 * result + (numChanged != null ? numChanged.hashCode() : 0);
        result = 31 * result + commitFileId;
        return result;
    }
}
