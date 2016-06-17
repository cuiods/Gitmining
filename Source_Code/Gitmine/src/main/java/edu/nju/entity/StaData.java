package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2016/6/17.
 */
@Entity
@Table(name = "sta_data", schema = "gitmining", catalog = "")
public class StaData {
    private String name;
    private Double dataOne;
    private Double dataTwo;
    private Double dataThree;
    private Double dataFour;
    private Double dataFive;

    @Id
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "data_one")
    public Double getDataOne() {
        return dataOne;
    }

    public void setDataOne(Double dataOne) {
        this.dataOne = dataOne;
    }

    @Basic
    @Column(name = "data_two")
    public Double getDataTwo() {
        return dataTwo;
    }

    public void setDataTwo(Double dataTwo) {
        this.dataTwo = dataTwo;
    }

    @Basic
    @Column(name = "data_three")
    public Double getDataThree() {
        return dataThree;
    }

    public void setDataThree(Double dataThree) {
        this.dataThree = dataThree;
    }

    @Basic
    @Column(name = "data_four")
    public Double getDataFour() {
        return dataFour;
    }

    public void setDataFour(Double dataFour) {
        this.dataFour = dataFour;
    }

    @Basic
    @Column(name = "data_five")
    public Double getDataFive() {
        return dataFive;
    }

    public void setDataFive(Double dataFive) {
        this.dataFive = dataFive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StaData staData = (StaData) o;

        if (name != null ? !name.equals(staData.name) : staData.name != null) return false;
        if (dataOne != null ? !dataOne.equals(staData.dataOne) : staData.dataOne != null) return false;
        if (dataTwo != null ? !dataTwo.equals(staData.dataTwo) : staData.dataTwo != null) return false;
        if (dataThree != null ? !dataThree.equals(staData.dataThree) : staData.dataThree != null) return false;
        if (dataFour != null ? !dataFour.equals(staData.dataFour) : staData.dataFour != null) return false;
        if (dataFive != null ? !dataFive.equals(staData.dataFive) : staData.dataFive != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (dataOne != null ? dataOne.hashCode() : 0);
        result = 31 * result + (dataTwo != null ? dataTwo.hashCode() : 0);
        result = 31 * result + (dataThree != null ? dataThree.hashCode() : 0);
        result = 31 * result + (dataFour != null ? dataFour.hashCode() : 0);
        result = 31 * result + (dataFive != null ? dataFive.hashCode() : 0);
        return result;
    }
}
