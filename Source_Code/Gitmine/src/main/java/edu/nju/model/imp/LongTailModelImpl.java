package edu.nju.model.imp;

import edu.nju.dao.service.LongTailDaoService;
import edu.nju.model.service.LongTailModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * long tail
 */
@Service
public class LongTailModelImpl implements LongTailModelService{

    @Resource
    private LongTailDaoService longTailDaoService;

    @Override
    public long[] twentyEightyRate() {
        long[] r1 = longTailDaoService.twentyEightyRate();
        long[] r2 = longTailDaoService.nintyEightRate();
        return new long[]{r1[0],r1[1],r2[0],r2[1]};
    }

    @Override
    public long[] nintyEightRate() {
        return longTailDaoService.nintyEightRate();
    }

    @Override
    public List<Integer> longtailDistribution() {
        List<Integer> result = longTailDaoService.followerDistribution();
        return result.subList(100,result.size());
    }
}
