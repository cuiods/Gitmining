package edu.nju.dao.service;

import java.util.List;
import java.util.Map;

/**
 * dao service used to stat repository popularity
 * @author cuihao
 */
public interface RepoPopuService {
    public Map<String,List> statPopuLanguage();
}
