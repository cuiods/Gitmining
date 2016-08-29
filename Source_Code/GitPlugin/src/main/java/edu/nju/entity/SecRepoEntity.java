package edu.nju.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by cuihao on 2016/8/3.
 */
@Entity
@Table(name = "sec_repo", schema = "gitmining", catalog = "")
public class SecRepoEntity {
    private long id;
    private String owner;
    private String name;
    private String htmlUrl;
    private String description;
    private Integer size;
    private Integer starCount;
    private Integer watchersCount;
    private String language;
    private Integer forkCount;
    private Timestamp createAt;
    private Timestamp updateAt;
    private String activityJson;
    private String puchJson;
    private String weeklyJson;
    private String punchJson;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "owner")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
    @Column(name = "html_url")
    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "size")
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Basic
    @Column(name = "star_count")
    public Integer getStarCount() {
        return starCount;
    }

    public void setStarCount(Integer starCount) {
        this.starCount = starCount;
    }

    @Basic
    @Column(name = "watchers_count")
    public Integer getWatchersCount() {
        return watchersCount;
    }

    public void setWatchersCount(Integer watchersCount) {
        this.watchersCount = watchersCount;
    }

    @Basic
    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    @Column(name = "fork_count")
    public Integer getForkCount() {
        return forkCount;
    }

    public void setForkCount(Integer forkCount) {
        this.forkCount = forkCount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SecRepoEntity that = (SecRepoEntity) o;

        if (id != that.id) return false;
        if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (htmlUrl != null ? !htmlUrl.equals(that.htmlUrl) : that.htmlUrl != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;
        if (starCount != null ? !starCount.equals(that.starCount) : that.starCount != null) return false;
        if (watchersCount != null ? !watchersCount.equals(that.watchersCount) : that.watchersCount != null)
            return false;
        if (language != null ? !language.equals(that.language) : that.language != null) return false;
        if (forkCount != null ? !forkCount.equals(that.forkCount) : that.forkCount != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (htmlUrl != null ? htmlUrl.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (starCount != null ? starCount.hashCode() : 0);
        result = 31 * result + (watchersCount != null ? watchersCount.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (forkCount != null ? forkCount.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "activity_json")
    public String getActivityJson() {
        return activityJson;
    }

    public void setActivityJson(String activityJson) {
        this.activityJson = activityJson;
    }

    @Basic
    @Column(name = "puch_json")
    public String getPuchJson() {
        return puchJson;
    }

    public void setPuchJson(String puchJson) {
        this.puchJson = puchJson;
    }

    @Basic
    @Column(name = "weekly_json")
    public String getWeeklyJson() {
        return weeklyJson;
    }

    public void setWeeklyJson(String weeklyJson) {
        this.weeklyJson = weeklyJson;
    }

    @Basic
    @Column(name = "punch_json")
    public String getPunchJson() {
        return punchJson;
    }

    public void setPunchJson(String punchJson) {
        this.punchJson = punchJson;
    }
}
