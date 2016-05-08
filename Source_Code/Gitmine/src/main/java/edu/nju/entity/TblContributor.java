package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2016/5/8.
 */
@Entity
@Table(name = "tbl_contributor", schema = "gitmining", catalog = "")
@IdClass(TblContributorPK.class)
public class TblContributor {
    private long repo;
    private long contributor;

    @Id
    @Column(name = "repo")
    public long getRepo() {
        return repo;
    }

    public void setRepo(long repo) {
        this.repo = repo;
    }

    @Id
    @Column(name = "contributor")
    public long getContributor() {
        return contributor;
    }

    public void setContributor(long contributor) {
        this.contributor = contributor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblContributor that = (TblContributor) o;

        if (repo != that.repo) return false;
        if (contributor != that.contributor) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (repo ^ (repo >>> 32));
        result = 31 * result + (int) (contributor ^ (contributor >>> 32));
        return result;
    }
}
