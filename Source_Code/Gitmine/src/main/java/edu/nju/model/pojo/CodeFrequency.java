package edu.nju.model.pojo;

/**
 * Created by Harry on 2016/5/14.
 * this class stores the data for code frequency chart which
 */
public class CodeFrequency {

    private String [] field;

    private Integer [] add;

    private Integer [] delete;

    public CodeFrequency() {
    }

    public CodeFrequency(String[] field, Integer[] add, Integer[] delete) {
        this.field = field;
        this.add = add;
        this.delete = delete;
    }

    public String[] getField() {
        return field;
    }

    public void setField(String[] field) {
        this.field = field;
    }

    public Integer[] getAdd() {
        return add;
    }

    public void setAdd(Integer[] add) {
        this.add = add;
    }

    public Integer[] getDelete() {
        return delete;
    }

    public void setDelete(Integer[] delete) {
        this.delete = delete;
    }
}
