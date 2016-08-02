package edu.nju.service;

import edu.nju.vo.PeopleOfRepo;

/**
 * compare logic service
 * @author cuihao
 */
public interface CompareService {
    double compareWatch(long watch);
    double compareStar(long star);
    double compareFork(long fork);
    PeopleOfRepo comparePeople(String owner, String name);
}
