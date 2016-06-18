package edu.nju.model.service;

import java.util.List;

/**
 * long tail model service
 */
public interface LongTailModelService {
    long[] twentyEightyRate();
    long[] nintyEightRate();
    List<Integer> longtailDistribution();
}
