package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2016/5/8.
 */
@Entity
@Table(name = "tbl_register", schema = "gitmining", catalog = "")
public class TblRegister {
    private long userId;
    private String loginName;

    @Id
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "login_name")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblRegister that = (TblRegister) o;

        if (userId != that.userId) return false;
        if (loginName != null ? !loginName.equals(that.loginName) : that.loginName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (loginName != null ? loginName.hashCode() : 0);
        return result;
    }
}
