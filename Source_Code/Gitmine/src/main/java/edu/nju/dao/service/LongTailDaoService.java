package edu.nju.dao.service;

import java.util.List;

/**
 * To test long tail theory.
 */
public interface LongTailDaoService {
    long[] twentyEightyRate();
    long[] nintyEightRate();
    List<Integer> followerDistribution();
}
