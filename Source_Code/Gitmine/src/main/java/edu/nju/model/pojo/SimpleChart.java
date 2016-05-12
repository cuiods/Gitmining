package edu.nju.model.pojo;

/**
 * Created by Harry on 2016/5/12.
 * this is a simple pojo for radar chart<br/>
 * the class only contains the field and value array for radar chart.
 */
public class SimpleChart {

    private String [] field;

    private double [] value;

    public SimpleChart(){

    }

    public SimpleChart(String[] field, double[] value) {
        this.field = field;
        this.value = value;
    }

    public String[] getField() {
        return field;
    }

    public void setField(String[] field) {
        this.field = field;
    }

    public double[] getValue() {
        return value;
    }

    public void setValue(double[] value) {
        this.value = value;
    }
}
