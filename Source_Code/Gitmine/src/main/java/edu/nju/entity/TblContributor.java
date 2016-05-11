package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2016/5/11.
 */
@Entity
@Table(name = "tbl_contributor", schema = "gitmining", catalog = "")
@IdClass(TblContributorPK.class)
public class TblContributor {
    private String repo;
    private String ownerName;
    private String contributor;

    @Id
    @Column(name = "repo")
    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    @Id
    @Column(name = "owner_name")
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Id
    @Column(name = "contributor")
    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblContributor that = (TblContributor) o;

        if (repo != null ? !repo.equals(that.repo) : that.repo != null) return false;
        if (ownerName != null ? !ownerName.equals(that.ownerName) : that.ownerName != null) return false;
        if (contributor != null ? !contributor.equals(that.contributor) : that.contributor != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = repo != null ? repo.hashCode() : 0;
        result = 31 * result + (ownerName != null ? ownerName.hashCode() : 0);
        result = 31 * result + (contributor != null ? contributor.hashCode() : 0);
        return result;
    }
}
