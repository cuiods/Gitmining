package edu.nju.vo;

/**
 * people statistic vo used to compare repositories
 * @author cuihao
 */
public class PeopleOfRepo {
    private int sumFollower;
    private int avgFollower;
    private double sumRange;
    private double avgRange;

    public int getSumFollower() {
        return sumFollower;
    }

    public void setSumFollower(int sumFollower) {
        this.sumFollower = sumFollower;
    }

    public int getAvgFollower() {
        return avgFollower;
    }

    public void setAvgFollower(int avgFollower) {
        this.avgFollower = avgFollower;
    }

    public double getSumRange() {
        return sumRange;
    }

    public void setSumRange(double sumRange) {
        this.sumRange = sumRange;
    }

    public double getAvgRange() {
        return avgRange;
    }

    public void setAvgRange(double avgRange) {
        this.avgRange = avgRange;
    }
}
