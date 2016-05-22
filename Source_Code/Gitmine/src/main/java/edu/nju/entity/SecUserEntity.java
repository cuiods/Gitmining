package edu.nju.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Harry on 2016/5/22.
 */
@Entity
@Table(name = "sec_user", schema = "gitmining", catalog = "")
public class SecUserEntity {
    private String login;
    private String avatarUrl;
    private String htmlUrl;
    private String type;
    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;
    private String bio;
    private Integer publicRepos;
    private Integer publicGists;
    private Integer followers;
    private Integer following;
    private Timestamp createAt;
    private Timestamp updateAt;
    private long id;

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Basic
    @Column(name = "html_url")
    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    @Column(name = "company")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Basic
    @Column(name = "blog")
    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "bio")
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Basic
    @Column(name = "public_repos")
    public Integer getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(Integer publicRepos) {
        this.publicRepos = publicRepos;
    }

    @Basic
    @Column(name = "public_gists")
    public Integer getPublicGists() {
        return publicGists;
    }

    public void setPublicGists(Integer publicGists) {
        this.publicGists = publicGists;
    }

    @Basic
    @Column(name = "followers")
    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    @Basic
    @Column(name = "following")
    public Integer getFollowing() {
        return following;
    }

    public void setFollowing(Integer following) {
        this.following = following;
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

        SecUserEntity that = (SecUserEntity) o;

        if (id != that.id) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (avatarUrl != null ? !avatarUrl.equals(that.avatarUrl) : that.avatarUrl != null) return false;
        if (htmlUrl != null ? !htmlUrl.equals(that.htmlUrl) : that.htmlUrl != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        if (blog != null ? !blog.equals(that.blog) : that.blog != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (bio != null ? !bio.equals(that.bio) : that.bio != null) return false;
        if (publicRepos != null ? !publicRepos.equals(that.publicRepos) : that.publicRepos != null) return false;
        if (publicGists != null ? !publicGists.equals(that.publicGists) : that.publicGists != null) return false;
        if (followers != null ? !followers.equals(that.followers) : that.followers != null) return false;
        if (following != null ? !following.equals(that.following) : that.following != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
        result = 31 * result + (htmlUrl != null ? htmlUrl.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (blog != null ? blog.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (bio != null ? bio.hashCode() : 0);
        result = 31 * result + (publicRepos != null ? publicRepos.hashCode() : 0);
        result = 31 * result + (publicGists != null ? publicGists.hashCode() : 0);
        result = 31 * result + (followers != null ? followers.hashCode() : 0);
        result = 31 * result + (following != null ? following.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }
}
