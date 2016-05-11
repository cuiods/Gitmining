package edu.nju.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by cuihao on 2016/5/11.
 */
public class TblContributorPK implements Serializable {
    private long repo;
    private long contributor;

    @Column(name = "repo")
    @Id
    public long getRepo() {
        return repo;
    }

    public void setRepo(long repo) {
        this.repo = repo;
    }

    @Column(name = "contributor")
    @Id
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

        TblContributorPK that = (TblContributorPK) o;

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
