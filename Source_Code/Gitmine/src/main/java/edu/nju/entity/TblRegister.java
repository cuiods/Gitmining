package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2016/5/12.
 */
@Entity
@Table(name = "tbl_register", schema = "gitmining", catalog = "")
public class TblRegister {
    private String loginName;
    private String password;
    private String email;

    @Id
    @Column(name = "login_name")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblRegister register = (TblRegister) o;

        if (loginName != null ? !loginName.equals(register.loginName) : register.loginName != null) return false;
        if (password != null ? !password.equals(register.password) : register.password != null) return false;
        if (email != null ? !email.equals(register.email) : register.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = loginName != null ? loginName.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
