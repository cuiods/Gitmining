package edu.nju.model.imp;

import edu.nju.common.json.JsonNodeParser;
import edu.nju.dao.service.RepoDaoService;
import edu.nju.entity.TblRepo;
import edu.nju.model.pojo.CommitChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Harry on 2016/5/16.
 * calculate radar chart
 */
@Service
public class RepoRadarImpl {

    private RepoDaoService repoDaoImpl;

    @Resource
    private JsonNodeParser jsonNodeParser;

    private double minLogSize;
    private double maxLogSize;
    private double minLogFork;
    private double maxLogFork;
    private double minLogPopular;
    private double maxLogPopular;
    private double minLogComplex;
    private double maxLogComplex;
    private double minLogActive;
    private double maxLogActive;

    @Autowired
    public RepoRadarImpl(RepoDaoService repoDaoImpl){
        this.repoDaoImpl = repoDaoImpl;
//        minLogSize = Math.log(repoDaoImpl.getMinRepoSize()+1);
        minLogSize = 0;
        maxLogSize = Math.log(repoDaoImpl.getMaxRepoSize()+1);
//        minLogFork = Math.log(repoDaoImpl.getMinRepoFork()+1);
        minLogFork = 0;
        maxLogFork = Math.log(repoDaoImpl.getMaxRepoFork()+1);
//        minLogPopular = Math.log(repoDaoImpl.getMinRepoPopular()+1);
        minLogPopular = 0;
        maxLogPopular = Math.log(repoDaoImpl.getMaxRepoPopular()+1);
//        minLogComplex = Math.log(repoDaoImpl.getMinRepoComplex()+1);
        minLogComplex = 0;
        maxLogComplex = Math.log(repoDaoImpl.getMaxRepoComplex()+1);
//        minLogActive = Math.log(repoDaoImpl.getMinActive()+1);
        minLogActive = 0;
        maxLogActive = Math.log(repoDaoImpl.getMaxActive()+1);
    }

    public double getRadarSize(String ownername, String reponame){
        double complex = Math.log(repoDaoImpl.getRepoComplex(ownername, reponame)+1);
        return (complex-minLogSize)/(maxLogSize-minLogSize);
    }

    public double getRadarFork(String ownername, String reponame){
        TblRepo repo = repoDaoImpl.getRepoBasicInfo(ownername, reponame);
        if (repo != null){

            if (maxLogFork == minLogFork){
                return 1.0;
            }

            int forkCount = repo.getNumFork();
            return (Math.log(forkCount+1)-minLogFork)/(maxLogFork-minLogFork);
        }
        else {
            return 0;
        }
    }

    public double getRadarPopular(String ownername, String reponame){
        TblRepo repo = repoDaoImpl.getRepoBasicInfo(ownername, reponame);
        if (repo != null){

            if (maxLogPopular == minLogPopular){
                return 1.0;
            }

            int popular = repo.getNumStar()+repo.getNumSubscriber();
            return (Math.log(popular+1)-minLogPopular)/(maxLogPopular-minLogPopular);
        }
        else {
            return 0;
        }
    }

    public double getRadarComplex(String ownername, String reponame){
        if (maxLogComplex == minLogComplex) {
            return 1.0;
        }

        double complex = repoDaoImpl.getRepoComplex(ownername, reponame);
        return (Math.log(complex+1)-minLogComplex)/(maxLogComplex-minLogComplex);
    }

    public double getRadarActive(String ownername, String reponame){

        if (minLogActive == maxLogActive){
            return 1.0;
        }

        Map<String,CommitChart> map = jsonNodeParser.getCommitByContributors(ownername, reponame);
        CommitChart chart = map.get("all");
        if (chart != null){
            long allCommit = chart.getCommitCount();
            double result = (Math.log(allCommit+1)-minLogActive)/(maxLogActive-minLogActive);
            return (result>1)?1.0:result;
        }
        else {
            return 0;
        }
    }

    public RepoDaoService getRepoDaoImpl() {
        return repoDaoImpl;
    }

    public void setRepoDaoImpl(RepoDaoService repoDaoImpl) {
        this.repoDaoImpl = repoDaoImpl;
    }

    public JsonNodeParser getJsonNodeParser() {
        return jsonNodeParser;
    }

    public void setJsonNodeParser(JsonNodeParser jsonNodeParser) {
        this.jsonNodeParser = jsonNodeParser;
    }
}
