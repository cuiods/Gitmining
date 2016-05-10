package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2016/5/10.
 */
@Entity
@Table(name = "tbl_collabrator", schema = "gitmining", catalog = "")
public class TblCollabrator {
    private long repo;
    private long collabrator;
    private long collabratorId;

    @Basic
    @Column(name = "repo")
    public long getRepo() {
        return repo;
    }

    public void setRepo(long repo) {
        this.repo = repo;
    }

    @Basic
    @Column(name = "collabrator")
    public long getCollabrator() {
        return collabrator;
    }

    public void setCollabrator(long collabrator) {
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

        if (repo != that.repo) return false;
        if (collabrator != that.collabrator) return false;
        if (collabratorId != that.collabratorId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (repo ^ (repo >>> 32));
        result = 31 * result + (int) (collabrator ^ (collabrator >>> 32));
        result = 31 * result + (int) (collabratorId ^ (collabratorId >>> 32));
        return result;
    }
}
