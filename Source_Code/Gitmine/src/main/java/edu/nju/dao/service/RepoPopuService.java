package edu.nju.dao.service;

import java.util.List;
import java.util.Map;

/**
 * dao service used to stat repository popularity
 * @author cuihao
 */
public interface RepoPopuService {
    /**
     * stat popular repositories' star number group by language
     * @return
     *  <li>language list</li>
     *  <li>star list with different language</li>
     */
    Map<String,List> statPopuLanguage();

    /**
     * stat star number's relationship with fork, contributor, collaborator, commit
     * @return
     * <li>with fork</li>
     * <li>with contributor</li>
     * <li>with collaborator</li>
     * <li>with commit</li>
     */
    Map<String,List> statStarRelation(int max);
}
