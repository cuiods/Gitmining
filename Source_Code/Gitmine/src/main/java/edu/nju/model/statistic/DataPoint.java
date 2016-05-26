package edu.nju.model.statistic;

/**
 * File        : DataPoint.java
 * @author cuihao
 * Description : Java实现一元线性回归的算法，座标点实体类，(可实现统计指标的预测)
 */
public class DataPoint {
    /** the x value */
    public float x;

    /** the y value */
    public float y;

    /**
     * Constructor.
     *
     * @param x
     *            the x value
     * @param y
     *            the y value
     */
    public DataPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
