package edu.nju.git.datavisitors.repovisitors;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.data.service.RepoDataService;

import java.util.List;

/**
 * Created by harry on 16-3-8.
 */
public class RepoNameOrderVisitor extends SimpleRepoVisitor {

    public RepoNameOrderVisitor(int page){
        this(page, true);
    }

    public RepoNameOrderVisitor(int page, boolean reverse) {
        super(page, reverse);
    }

    @Override
    public List<RepoBriefVO> visit(RepoDataService repoDataService) {
        return null;
    }
}
