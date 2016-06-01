package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by Harry on 2016/6/1.
 */
@Entity
@Table(name = "register_star_user", schema = "gitmining", catalog = "")
@IdClass(RegisterStarUserEntityPK.class)
public class RegisterStarUserEntity {
    private String webUsername;
    private String username;

    @Id
    @Column(name = "web_username")
    public String getWebUsername() {
        return webUsername;
    }

    public void setWebUsername(String webUsername) {
        this.webUsername = webUsername;
    }

    @Id
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegisterStarUserEntity that = (RegisterStarUserEntity) o;

        if (webUsername != null ? !webUsername.equals(that.webUsername) : that.webUsername != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = webUsername != null ? webUsername.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}
