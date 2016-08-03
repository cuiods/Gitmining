package edu.nju.serviceImpl;

import edu.nju.dao.CompareDao;
import edu.nju.service.CompareService;
import edu.nju.vo.PeopleOfRepo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * compare service impl
 * @author cuihao
 */
//TODO 2 digits!!!!!!!!!
@Service
public class CompareServiceImpl implements CompareService {

    @Resource
    private CompareDao compareDao;

    public double compareWatch(long watch) {
        long sum = compareDao.sumRepo();
        long num = compareDao.rangeOfWatch(watch);
        return num * 1.0 / sum * 100;
    }

    public double compareStar(long star) {
        long sum = compareDao.sumRepo();
        long num = compareDao.rangeOfStar(star);
        return num * 1.0 / sum * 100;
    }

    public double compareFork(long fork) {
        long sum = compareDao.sumRepo();
        long num = compareDao.rangeOfFork(fork);
        return num * 1.0 / sum * 100;
    }

    public PeopleOfRepo comparePeople(String owner, String name) {
        return null;
    }
}
