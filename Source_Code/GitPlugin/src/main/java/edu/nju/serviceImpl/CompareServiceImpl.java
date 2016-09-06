package edu.nju.serviceImpl;

import edu.nju.dao.CompareDao;
import edu.nju.service.CompareService;
import edu.nju.vo.PeopleOfRepo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;

/**
 * compare service impl
 * @author cuihao
 */
@Service
public class CompareServiceImpl implements CompareService {

    @Resource
    private CompareDao compareDao;

    public double compareWatch(long watch) {
        long sum = compareDao.sumRepo();
        long num = compareDao.rangeOfWatch(watch);
        return twoDigits(num * 1.0 / sum * 100);
    }

    public double compareStar(long star) {
        long sum = compareDao.sumRepo();
        long num = compareDao.rangeOfStar(star);
        return twoDigits(num * 1.0 / sum * 100);
    }

    public double compareFork(long fork) {
        long sum = compareDao.sumRepo();
        long num = compareDao.rangeOfFork(fork);
        return twoDigits(num * 1.0 / sum * 100);
    }

    public double comparePeople(String owner, String name) {
        long sum = compareDao.sumRepo();
        long num = compareDao.rangeOfFollwer(compareDao.peopleFollower(owner, name));
        return twoDigits(num * 1.0 / sum * 100);
    }

    private double twoDigits(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return Double.parseDouble(decimalFormat.format(number));
    }
}
