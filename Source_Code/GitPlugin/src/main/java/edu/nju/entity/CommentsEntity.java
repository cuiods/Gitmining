package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2016/8/6.
 */
@Entity
@Table(name = "comments", schema = "gitmining", catalog = "")
public class CommentsEntity {
    private Long repoId;
    private String comment;
    private long id;
    private Byte source;

    @Basic
    @Column(name = "repo_id")
    public Long getRepoId() {
        return repoId;
    }

    public void setRepoId(Long repoId) {
        this.repoId = repoId;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentsEntity that = (CommentsEntity) o;

        if (repoId != null ? !repoId.equals(that.repoId) : that.repoId != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = repoId != null ? repoId.hashCode() : 0;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "source")
    public Byte getSource() {
        return source;
    }

    public void setSource(Byte source) {
        this.source = source;
    }
}
