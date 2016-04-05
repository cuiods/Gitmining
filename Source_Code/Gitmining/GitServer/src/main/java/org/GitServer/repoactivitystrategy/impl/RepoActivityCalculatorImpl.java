package org.GitServer.repoactivitystrategy.impl;

import edu.nju.git.VO.RepoVO;
import org.GitServer.repoactivitystrategy.service.RepoActivityCalculator;

import java.util.List;

/**
 * Created by Harry on 2016/4/5.
 */
public class RepoActivityCalculatorImpl implements RepoActivityCalculator {
    @Override
    public RepoVO generateLineChart(RepoVO vo, List<String> commits, List<String> pulls, List<String> issues) {
        return null;
    }
}
