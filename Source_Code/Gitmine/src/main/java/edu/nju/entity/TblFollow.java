package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2016/5/11.
 */
@Entity
@Table(name = "tbl_follow", schema = "gitmining", catalog = "")
@IdClass(TblFollowPK.class)
public class TblFollow {
    private long userId;
    private long followingId;

    @Id
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "following_id")
    public long getFollowingId() {
        return followingId;
    }

    public void setFollowingId(long followingId) {
        this.followingId = followingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblFollow tblFollow = (TblFollow) o;

        if (userId != tblFollow.userId) return false;
        if (followingId != tblFollow.followingId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (followingId ^ (followingId >>> 32));
        return result;
    }
}
