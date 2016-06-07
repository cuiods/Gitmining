package edu.nju.model.statistic;

import edu.nju.dao.service.RepoPopuService;
import edu.nju.model.service.RepoPopuModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * calculation star and fork and watchers
 */
@Service
public class StarRegression implements StarRegressionService{
    @Resource
    private RepoPopuService repoPopuService;

    @Override
    public RegressionLine getForkRegression() {
        RegressionLine line = new RegressionLine();
        List<List> starData = repoPopuService.statStarRelation(-1).get("fork");
        for (int i = 0; i < starData.size(); i++) {
            List data = starData.get(i);
            line.addDataPoint(new DataPoint((Integer)data.get(0),(Integer)data.get(1)));
        }
        return line;
    }

    @Override
    public RegressionLine getWatcherRegression() {
        RegressionLine line = new RegressionLine();
        List<List> starData = repoPopuService.statStarRelation(-1).get("watcher");
        for (int i = 0; i < starData.size(); i++) {
            List data = starData.get(i);
            line.addDataPoint(new DataPoint((Integer)data.get(0),(Integer)data.get(1)));
        }
        return line;
    }

    @Override
    public RegressionLine getFollowerRegression() {
        RegressionLine line = new RegressionLine();
        List<Object[]> starData = repoPopuService.followerRegression(Integer.MAX_VALUE);
        for (int i = 0; i < starData.size(); i++) {
            Object[] data = starData.get(i);
            line.addDataPoint(new DataPoint(((BigDecimal)data[0]).floatValue(),(Integer)data[1]));
        }
        return line;
    }
}
