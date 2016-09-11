package edu.nju.serviceImpl;

import edu.nju.dao.RepoDao;
import edu.nju.entity.SecRepoEntity;
import edu.nju.service.AnalyseService;
import edu.nju.vo.PopularAttribute;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author cuihao
 */
@Repository
public class AnalyseServiceImpl implements AnalyseService {

    @Resource
    private RepoDao repoDao;

    @Override
    public double sigmoid(double value) {
        return 1.0/(1+Math.exp(-value));
    }

    @Override
    public double popularRate(String owner, String name) {
        SecRepoEntity entity = repoDao.getRepo(owner, name);
        PopularAttribute attribute = null;
        if (entity!=null) {
            attribute = new PopularAttribute(entity.getSize(),entity.getForkCount(),entity.getSumFollower(),entity.getAvgFollower(),entity.getWeightFollower());
        } else {
            int[] forksize = repoDao.getForkandSize(owner, name);
            int sumFollower = 0;
            double sumContribution = 0;
            double sumWeight = 0;
            Map<String,Integer> map = repoDao.getRepoFollowers(owner, name);
            for (Map.Entry<String,Integer> entry: map.entrySet()) {
                int followers = repoDao.getFollowerNum(entry.getKey());
                sumFollower += followers;
                sumContribution += entry.getValue();
                sumWeight += entry.getValue() * followers;
            }
            sumWeight = sumWeight / sumContribution;
            attribute = new PopularAttribute(forksize[1],forksize[0],sumFollower,sumFollower*1.0/map.size(),sumWeight);
        }
        System.out.println(attribute);
        return calculateRate(attribute);
    }

    private double calculateRate(PopularAttribute attribute) {
        double result = 0.5;
        if (attribute==null) return result;
        double node1 = sigmoid(52.7172*attribute.getFork()+(-3.0552)*attribute.getSize()+(-58.7539)*attribute.getSum()
                +(-122.2211)*attribute.getAvg()+(160.3709)*attribute.getWeight());
        double node2 = sigmoid(-5.4820*attribute.getFork()+(-0.7869)*attribute.getSize()+(-1.1953)*attribute.getSum()
                +(2.1421)*attribute.getAvg()+(-1.3459)*attribute.getWeight());
        double node3 = sigmoid(51.0565*attribute.getFork()+(-24.5627)*attribute.getSize()+(0.8719)*attribute.getSum()
                +(2.1335)*attribute.getAvg()+(-1.5291)*attribute.getWeight());
        result = sigmoid(0.6596*node1+2.4517*node2+1.6151*node3);
        return result;
    }
}
