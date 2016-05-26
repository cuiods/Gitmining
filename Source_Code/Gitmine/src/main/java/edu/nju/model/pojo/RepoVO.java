package edu.nju.model.pojo;

/**
 * Created by Harry on 2016/5/17.
 * the show value for repo
 */
public class RepoVO {
    private String ownerName;
    private String ownerAvatarUrl;
    private String reponame;
    private int size;
    private String description;
    private String language;
    private String url;
    private String createAt;
    private String updateAt;
    private int numStar;
    private int numFork;
    private int numWatcher;

    public RepoVO() {
    }

    public RepoVO(String ownerName, String ownerAvatarUrl, String reponame, int size, String description,
                  String language, String url, String createAt, String updateAt, int numStar, int numFork,
                  int numWatcher) {
        this.ownerName = ownerName;
        this.ownerAvatarUrl = ownerAvatarUrl;
        this.reponame = reponame;
        this.size = size;
        this.description = description;
        this.language = language;
        this.url = url;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.numStar = numStar;
        this.numFork = numFork;
        this.numWatcher = numWatcher;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerAvatarUrl() {
        return ownerAvatarUrl;
    }

    public void setOwnerAvatarUrl(String ownerAvatarUrl) {
        this.ownerAvatarUrl = ownerAvatarUrl;
    }

    public String getReponame() {
        return reponame;
    }

    public void setReponame(String reponame) {
        this.reponame = reponame;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public int getNumStar() {
        return numStar;
    }

    public void setNumStar(int numStar) {
        this.numStar = numStar;
    }

    public int getNumFork() {
        return numFork;
    }

    public void setNumFork(int numFork) {
        this.numFork = numFork;
    }

    public int getNumWatcher() {
        return numWatcher;
    }

    public void setNumWatcher(int numWatcher) {
        this.numWatcher = numWatcher;
    }
}
