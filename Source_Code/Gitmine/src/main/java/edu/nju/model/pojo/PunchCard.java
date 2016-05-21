package edu.nju.model.pojo;

/**
 * Created by Harry on 2016/5/21.
 * this is a statistic pojo
 */
public class PunchCard {

    private String [] rowField = {"0","1","2","3","4","5","6","7","8","9","10","11","12",
            "13","14","15","16","17","18","19","20","21","22","23"};
    private String [] columnField = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};

    private Integer [][] value;

    public PunchCard() {
        value = new Integer[7][24];
    }
}
