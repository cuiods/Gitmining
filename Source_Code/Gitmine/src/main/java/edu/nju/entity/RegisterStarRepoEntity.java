package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by Harry on 2016/5/29.
 */
@Entity
@Table(name = "register_star_repo", schema = "gitmining", catalog = "")
@IdClass(RegisterStarRepoEntityPK.class)
public class RegisterStarRepoEntity {
    private String webUsername;
    private String repoName;
    private String repoOwner;

    @Id
    @Column(name = "web_username")
    public String getWebUsername() {
        return webUsername;
    }

    public void setWebUsername(String webUsername) {
        this.webUsername = webUsername;
    }

    @Id
    @Column(name = "repo_name")
    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    @Id
    @Column(name = "repo_owner")
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

        RegisterStarRepoEntity that = (RegisterStarRepoEntity) o;

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
