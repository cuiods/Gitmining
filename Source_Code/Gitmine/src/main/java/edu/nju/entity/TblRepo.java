package edu.nju.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by cuihao on 2016/5/8.
 */
@Entity
@Table(name = "tbl_repo", schema = "gitmining", catalog = "")
public class TblRepo {
    private long repoId;
    private String name;
    private long ownerId;
    private int size;
    private byte language;
    private String url;
    private String description;
    private Timestamp createAt;
    private Timestamp updateAt;
    private Integer numStar;
    private Integer numFork;
    private Integer numSubscriber;
    private Integer numContributor;
    private Integer numCollaborator;
    private Integer numCommit;
    private Integer numPull;
    private Integer numIssue;

    @Id
    @Column(name = "repo_id")
    public long getRepoId() {
        return repoId;
    }

    public void setRepoId(long repoId) {
        this.repoId = repoId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "owner_id")
    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    @Basic
    @Column(name = "size")
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Basic
    @Column(name = "language")
    public byte getLanguage() {
        return language;
    }

    public void setLanguage(byte language) {
        this.language = language;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "create_at")
    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    @Basic
    @Column(name = "update_at")
    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    @Basic
    @Column(name = "num_star")
    public Integer getNumStar() {
        return numStar;
    }

    public void setNumStar(Integer numStar) {
        this.numStar = numStar;
    }

    @Basic
    @Column(name = "num_fork")
    public Integer getNumFork() {
        return numFork;
    }

    public void setNumFork(Integer numFork) {
        this.numFork = numFork;
    }

    @Basic
    @Column(name = "num_subscriber")
    public Integer getNumSubscriber() {
        return numSubscriber;
    }

    public void setNumSubscriber(Integer numSubscriber) {
        this.numSubscriber = numSubscriber;
    }

    @Basic
    @Column(name = "num_contributor")
    public Integer getNumContributor() {
        return numContributor;
    }

    public void setNumContributor(Integer numContributor) {
        this.numContributor = numContributor;
    }

    @Basic
    @Column(name = "num_collaborator")
    public Integer getNumCollaborator() {
        return numCollaborator;
    }

    public void setNumCollaborator(Integer numCollaborator) {
        this.numCollaborator = numCollaborator;
    }

    @Basic
    @Column(name = "num_commit")
    public Integer getNumCommit() {
        return numCommit;
    }

    public void setNumCommit(Integer numCommit) {
        this.numCommit = numCommit;
    }

    @Basic
    @Column(name = "num_pull")
    public Integer getNumPull() {
        return numPull;
    }

    public void setNumPull(Integer numPull) {
        this.numPull = numPull;
    }

    @Basic
    @Column(name = "num_issue")
    public Integer getNumIssue() {
        return numIssue;
    }

    public void setNumIssue(Integer numIssue) {
        this.numIssue = numIssue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblRepo tblRepo = (TblRepo) o;

        if (repoId != tblRepo.repoId) return false;
        if (ownerId != tblRepo.ownerId) return false;
        if (size != tblRepo.size) return false;
        if (language != tblRepo.language) return false;
        if (name != null ? !name.equals(tblRepo.name) : tblRepo.name != null) return false;
        if (url != null ? !url.equals(tblRepo.url) : tblRepo.url != null) return false;
        if (description != null ? !description.equals(tblRepo.description) : tblRepo.description != null) return false;
        if (createAt != null ? !createAt.equals(tblRepo.createAt) : tblRepo.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(tblRepo.updateAt) : tblRepo.updateAt != null) return false;
        if (numStar != null ? !numStar.equals(tblRepo.numStar) : tblRepo.numStar != null) return false;
        if (numFork != null ? !numFork.equals(tblRepo.numFork) : tblRepo.numFork != null) return false;
        if (numSubscriber != null ? !numSubscriber.equals(tblRepo.numSubscriber) : tblRepo.numSubscriber != null)
            return false;
        if (numContributor != null ? !numContributor.equals(tblRepo.numContributor) : tblRepo.numContributor != null)
            return false;
        if (numCollaborator != null ? !numCollaborator.equals(tblRepo.numCollaborator) : tblRepo.numCollaborator != null)
            return false;
        if (numCommit != null ? !numCommit.equals(tblRepo.numCommit) : tblRepo.numCommit != null) return false;
        if (numPull != null ? !numPull.equals(tblRepo.numPull) : tblRepo.numPull != null) return false;
        if (numIssue != null ? !numIssue.equals(tblRepo.numIssue) : tblRepo.numIssue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (repoId ^ (repoId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (ownerId ^ (ownerId >>> 32));
        result = 31 * result + size;
        result = 31 * result + (int) language;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (numStar != null ? numStar.hashCode() : 0);
        result = 31 * result + (numFork != null ? numFork.hashCode() : 0);
        result = 31 * result + (numSubscriber != null ? numSubscriber.hashCode() : 0);
        result = 31 * result + (numContributor != null ? numContributor.hashCode() : 0);
        result = 31 * result + (numCollaborator != null ? numCollaborator.hashCode() : 0);
        result = 31 * result + (numCommit != null ? numCommit.hashCode() : 0);
        result = 31 * result + (numPull != null ? numPull.hashCode() : 0);
        result = 31 * result + (numIssue != null ? numIssue.hashCode() : 0);
        return result;
    }
}
