package edu.nju.model.pojo;

/**
 * Created by Harry on 2016/5/14.
 * this class stores data for line chart about commit for each contributor of a repo.<br/>
 * specially, it can also represent the total data of all contributors
 */
public class CommitChart {

    private String contributorName;

    private String avatarUrl;

    private long commitCount;

    private String [] field;

    private Integer [] value;

    public CommitChart() {
    }

    public CommitChart(String contributorName, String avatarUrl, long commitCount, String[] field, Integer[] value) {
        this.contributorName = contributorName;
        this.avatarUrl = avatarUrl;
        this.commitCount = commitCount;
        this.field = field;
        this.value = value;
    }

    public String getContributorName() {
        return contributorName;
    }

    public void setContributorName(String contributorName) {
        this.contributorName = contributorName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public long getCommitCount() {
        return commitCount;
    }

    public void setCommitCount(long commitCount) {
        this.commitCount = commitCount;
    }

    public String[] getField() {
        return field;
    }

    public void setField(String[] field) {
        this.field = field;
    }

    public Integer[] getValue() {
        return value;
    }

    public void setValue(Integer[] value) {
        this.value = value;
    }
}
