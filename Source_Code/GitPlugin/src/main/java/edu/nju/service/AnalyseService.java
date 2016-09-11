package edu.nju.service;

/**
 * analyse repository
 * Created by cuihao on 2016/9/10.
 */
public interface AnalyseService {
    double sigmoid(double value);
    double popularRate(String owner, String name);
}
