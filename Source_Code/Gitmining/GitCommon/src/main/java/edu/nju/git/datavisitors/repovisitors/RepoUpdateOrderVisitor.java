package edu.nju.git.datavisitors.repovisitors;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.data.service.RepoDataService;
import edu.nju.git.type.SortType;

import java.util.List;

/**
 * Created by harry on 16-3-8.
 */
public class RepoUpdateOrderVisitor extends SimpleRepoVisitor {

    public RepoUpdateOrderVisitor(int page, boolean reverse) {
        super(page, reverse);
    }

    @Override
    public List<RepoBriefVO> visit(RepoDataService repoDataService) {
        return super.visit(repoDataService, SortType.UPDATE_TIME);
    }
}