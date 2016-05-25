package edu.nju.model.imp;

import edu.nju.dao.service.RepoPopuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2016/5/25.
 */
@Service
public class RepoPopuModelImp implements RepoPopuService {
    @Resource
    private RepoPopuService repoPopuImp;
    @Override
    public Map<String, List> statPopuLanguage() {
        return repoPopuImp.statPopuLanguage();
    }
}
