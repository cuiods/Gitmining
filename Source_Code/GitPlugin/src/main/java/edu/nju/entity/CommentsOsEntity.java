package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by Harry on 2016/8/31.
 */
@Entity
@Table(name = "comments_os", schema = "gitmining", catalog = "")
public class CommentsOsEntity {
    private String pName;

    @Basic
    @Column(name = "p_name")
    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    private String comment;

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }    private long id;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentsOsEntity that = (CommentsOsEntity) o;

        if (id != that.id) return false;
        if (pName != null ? !pName.equals(that.pName) : that.pName != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pName != null ? pName.hashCode() : 0;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }
}
