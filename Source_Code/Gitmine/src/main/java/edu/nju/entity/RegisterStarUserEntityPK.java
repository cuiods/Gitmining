package edu.nju.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Harry on 2016/6/1.
 */
public class RegisterStarUserEntityPK implements Serializable {
    private String webUsername;
    private String username;

    @Column(name = "web_username")
    @Id
    public String getWebUsername() {
        return webUsername;
    }

    public void setWebUsername(String webUsername) {
        this.webUsername = webUsername;
    }

    @Column(name = "username")
    @Id
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

        RegisterStarUserEntityPK that = (RegisterStarUserEntityPK) o;

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
