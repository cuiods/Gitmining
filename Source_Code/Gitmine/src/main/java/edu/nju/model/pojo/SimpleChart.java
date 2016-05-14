package edu.nju.model.pojo;

/**
 * Created by Harry on 2016/5/14.
 */
public class SimpleChart {

    private String [] field;

    private long [] value;

    public SimpleChart(){

    }

    public SimpleChart(String[] field, long[] value) {
        this.field = field;
        this.value = value;
    }

    public String[] getField() {
        return field;
    }

    public void setField(String[] field) {
        this.field = field;
    }

    public long[] getValue() {
        return value;
    }

    public void setValue(long[] value) {
        this.value = value;
    }
}
