package org.GitServer.repoactivitystrategy.service;

import edu.nju.git.VO.RepoVO;

import java.util.List;

/**
 * this interface is for calculating line chart for each repovo of its activity
 */
public interface RepoActivityCalculator {

    /**
     * use commits, pulls and issues to calculate line chart fields and values, set them to the given vo and
     * then return the vo
     * @param vo
     * @param commits
     * @param pulls
     * @param issues
     * @return
     */
    public RepoVO generateLineChart(RepoVO vo, List<String> commits, List<String> pulls, List<String> issues);
}
