package edu.nju.model.pojo;

/**
 * Created by Harry on 2016/5/17.
 * the show value for user
 */
public class UserVO {

    private String login;
    private String name;
    private String avatarUrl;
    private String blog;
    private String email;
    private String location;
    private String company;
    private String bio;
    private String type;
    private int publicRepo;
    private int publicGist;
    private int follower;
    private int following;
    private String createAt;
    private String updateAt;

    public UserVO() {
    }

    public UserVO(String login, String name, String avatarUrl, String blog, String email, String location,
                  String company, String bio, String type, int publicRepo, int publicGist, int follower,
                  int following, String createAt, String updateAt) {
        this.login = login;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.blog = blog;
        this.email = email;
        this.location = location;
        this.company = company;
        this.bio = bio;
        this.type = type;
        this.publicRepo = publicRepo;
        this.publicGist = publicGist;
        this.follower = follower;
        this.following = following;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPublicRepo() {
        return publicRepo;
    }

    public void setPublicRepo(int publicRepo) {
        this.publicRepo = publicRepo;
    }

    public int getPublicGist() {
        return publicGist;
    }

    public void setPublicGist(int publicGist) {
        this.publicGist = publicGist;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
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
}
