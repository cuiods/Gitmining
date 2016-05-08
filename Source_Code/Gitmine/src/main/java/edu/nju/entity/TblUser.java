package edu.nju.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by cuihao on 2016/5/8.
 */
@Entity
@Table(name = "tbl_user", schema = "gitmining", catalog = "")
public class TblUser {
    private long userId;
    private String loginName;
    private String avatarUrl;
    private String blog;
    private String location;
    private String company;
    private byte type;
    private Integer publicRepo;
    private Integer publicGist;
    private Integer follower;
    private Integer following;
    private String email;
    private String bio;
    private Timestamp createAt;
    private Timestamp updateAt;
    private Integer numSubscriber;
    private Integer numContributor;
    private Integer numCollabrator;
    private byte state;

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

    @Basic
    @Column(name = "avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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
    @Column(name = "company")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Basic
    @Column(name = "type")
    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    @Basic
    @Column(name = "public_repo")
    public Integer getPublicRepo() {
        return publicRepo;
    }

    public void setPublicRepo(Integer publicRepo) {
        this.publicRepo = publicRepo;
    }

    @Basic
    @Column(name = "public_gist")
    public Integer getPublicGist() {
        return publicGist;
    }

    public void setPublicGist(Integer publicGist) {
        this.publicGist = publicGist;
    }

    @Basic
    @Column(name = "follower")
    public Integer getFollower() {
        return follower;
    }

    public void setFollower(Integer follower) {
        this.follower = follower;
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

    @Basic
    @Column(name = "num_subscriber")
    public Integer getNumSubscriber() {
        return numSubscriber;
    }

    public void setNumSubscriber(Integer numSubscriber) {
        this.numSubscriber = numSubscriber;
    }

    @Basic
    @Column(name = "num_contributor")
    public Integer getNumContributor() {
        return numContributor;
    }

    public void setNumContributor(Integer numContributor) {
        this.numContributor = numContributor;
    }

    @Basic
    @Column(name = "num_collabrator")
    public Integer getNumCollabrator() {
        return numCollabrator;
    }

    public void setNumCollabrator(Integer numCollabrator) {
        this.numCollabrator = numCollabrator;
    }

    @Basic
    @Column(name = "state")
    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblUser tblUser = (TblUser) o;

        if (userId != tblUser.userId) return false;
        if (type != tblUser.type) return false;
        if (state != tblUser.state) return false;
        if (loginName != null ? !loginName.equals(tblUser.loginName) : tblUser.loginName != null) return false;
        if (avatarUrl != null ? !avatarUrl.equals(tblUser.avatarUrl) : tblUser.avatarUrl != null) return false;
        if (blog != null ? !blog.equals(tblUser.blog) : tblUser.blog != null) return false;
        if (location != null ? !location.equals(tblUser.location) : tblUser.location != null) return false;
        if (company != null ? !company.equals(tblUser.company) : tblUser.company != null) return false;
        if (publicRepo != null ? !publicRepo.equals(tblUser.publicRepo) : tblUser.publicRepo != null) return false;
        if (publicGist != null ? !publicGist.equals(tblUser.publicGist) : tblUser.publicGist != null) return false;
        if (follower != null ? !follower.equals(tblUser.follower) : tblUser.follower != null) return false;
        if (following != null ? !following.equals(tblUser.following) : tblUser.following != null) return false;
        if (email != null ? !email.equals(tblUser.email) : tblUser.email != null) return false;
        if (bio != null ? !bio.equals(tblUser.bio) : tblUser.bio != null) return false;
        if (createAt != null ? !createAt.equals(tblUser.createAt) : tblUser.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(tblUser.updateAt) : tblUser.updateAt != null) return false;
        if (numSubscriber != null ? !numSubscriber.equals(tblUser.numSubscriber) : tblUser.numSubscriber != null)
            return false;
        if (numContributor != null ? !numContributor.equals(tblUser.numContributor) : tblUser.numContributor != null)
            return false;
        if (numCollabrator != null ? !numCollabrator.equals(tblUser.numCollabrator) : tblUser.numCollabrator != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (loginName != null ? loginName.hashCode() : 0);
        result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
        result = 31 * result + (blog != null ? blog.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (int) type;
        result = 31 * result + (publicRepo != null ? publicRepo.hashCode() : 0);
        result = 31 * result + (publicGist != null ? publicGist.hashCode() : 0);
        result = 31 * result + (follower != null ? follower.hashCode() : 0);
        result = 31 * result + (following != null ? following.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (bio != null ? bio.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (numSubscriber != null ? numSubscriber.hashCode() : 0);
        result = 31 * result + (numContributor != null ? numContributor.hashCode() : 0);
        result = 31 * result + (numCollabrator != null ? numCollabrator.hashCode() : 0);
        result = 31 * result + (int) state;
        return result;
    }
}
