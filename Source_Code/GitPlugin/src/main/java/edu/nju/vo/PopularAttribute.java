package edu.nju.vo;

/**
 * @author cuihao
 */
public class PopularAttribute {
    private int size;
    private int fork;
    private int sum;
    private double avg;
    private double weight;

    public PopularAttribute(int size, int fork, int sum, double avg, double weight) {
        this.size = size;
        this.fork = fork;
        this.sum = sum;
        this.avg = avg;
        this.weight = weight;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getFork() {
        return fork;
    }

    public void setFork(int fork) {
        this.fork = fork;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "PopularAttribute{" +
                "size=" + size +
                ", fork=" + fork +
                ", sum=" + sum +
                ", avg=" + avg +
                ", weight=" + weight +
                '}';
    }
}
