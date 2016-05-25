package edu.nju.model.imp;

import edu.nju.dao.service.RepoPopuService;
import edu.nju.model.service.RepoPopuModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2016/5/25.
 */
@Service
public class RepoPopuModelImp implements RepoPopuModelService {
    @Resource
    private RepoPopuService repoPopuImp;

    @Override
    public Map<String, List> statLanguagePopularity() {
        return repoPopuImp.statPopuLanguage();
    }
}
