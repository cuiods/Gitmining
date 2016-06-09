package edu.nju.model.pojo;

/**
 * Created by Harry on 2016/5/28.
 * this is a vo for repo but very simple, it doesn't has avatar
 */
public class SimpleRepoVO {
    private String ownerName;
    private String reponame;
    private int size;
    private String description;
    private String language;
    private String createAt;
    private String updateAt;
    private int numStar;
    private int numFork;
    private int numWatcher;
    private boolean isStared = false;

    public SimpleRepoVO() {
    }

    public SimpleRepoVO(String ownerName, String reponame, int size, String description,
                        String language, String createAt, String updateAt, int numStar,
                        int numFork, int numWatcher) {
        this.ownerName = ownerName;
        this.reponame = reponame;
        this.size = size;
        this.description = description;
        this.language = language;
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

    public boolean getIsStared() {
        return this.isStared;
    }

    public void setIsStared(boolean isStared) {
        this.isStared = isStared;
    }
}
