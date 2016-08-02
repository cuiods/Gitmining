package edu.nju.serviceImpl;

import edu.nju.service.CompareService;
import edu.nju.vo.PeopleOfRepo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * compare service impl
 * @author cuihao
 */
@Service
public class CompareServiceImpl implements CompareService {
    public double compareWatch(long watch) {
        return 0;
    }

    public double compareStar(long star) {
        return 0;
    }

    public double compareFork(long fork) {
        return 0;
    }

    public PeopleOfRepo comparePeople(String owner, String name) {
        return null;
    }
}
