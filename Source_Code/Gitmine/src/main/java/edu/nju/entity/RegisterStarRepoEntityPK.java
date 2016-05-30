package edu.nju.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Harry on 2016/5/29.
 */
public class RegisterStarRepoEntityPK implements Serializable {
    private String webUsername;
    private String repoName;
    private String repoOwner;

    @Column(name = "web_username")
    @Id
    public String getWebUsername() {
        return webUsername;
    }

    public void setWebUsername(String webUsername) {
        this.webUsername = webUsername;
    }

    @Column(name = "repo_name")
    @Id
    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    @Column(name = "repo_owner")
    @Id
    public String getRepoOwner() {
        return repoOwner;
    }

    public void setRepoOwner(String repoOwner) {
        this.repoOwner = repoOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegisterStarRepoEntityPK that = (RegisterStarRepoEntityPK) o;

        if (webUsername != null ? !webUsername.equals(that.webUsername) : that.webUsername != null) return false;
        if (repoName != null ? !repoName.equals(that.repoName) : that.repoName != null) return false;
        if (repoOwner != null ? !repoOwner.equals(that.repoOwner) : that.repoOwner != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = webUsername != null ? webUsername.hashCode() : 0;
        result = 31 * result + (repoName != null ? repoName.hashCode() : 0);
        result = 31 * result + (repoOwner != null ? repoOwner.hashCode() : 0);
        return result;
    }
}
