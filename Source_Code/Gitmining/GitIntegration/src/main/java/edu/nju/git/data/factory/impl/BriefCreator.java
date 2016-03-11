package edu.nju.git.data.factory.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.data.localDataReader.MapLocalReader;
import edu.nju.git.tools.POVOConverter;

public class BriefCreator {

	  /**
     * load the load map resources
     */
    private MapLocalReader loader = MapLocalReader.getInstance();
    private static BriefCreator instance;
    private synchronized static void createdInstance(){
    	if(instance==null){
    		instance = new BriefCreator();
    	}
    }
    
    public static BriefCreator getInstance(){
    	if(instance==null){
    		createdInstance();
    	}
    	return instance;
    }
    /**
     * find repoBriefVOs by names
     * <br/><b>precondition</b>：
     * <br/><b>postcondition</b>：return a repoBriefVO
     * @param repos the names of repoBriefVO
     * @return
     * @date 2016-03-11
     */
    private List<RepoBriefVO> convertNamesToRepos(List<String> repos){
    	if(repos==null){
    		return new ArrayList<RepoBriefVO>();
    	}
    	Map<String, RepoBriefPO> nameToRepo = loader.getNameToRepo();
    	List<RepoBriefVO> repoBriefVOs = new ArrayList<RepoBriefVO>();
    	for (String repoName : repos) {
    		RepoBriefPO repoBriefPO = nameToRepo.get(repoName);
    		if(repoBriefPO==null) continue;
    		RepoBriefVO briefVO = POVOConverter.convert(repoBriefPO);
    		repoBriefVOs.add(briefVO);
		}
        return repoBriefVOs;
    }
    
    
    
    public List<RepoBriefVO> getUserOwnRepos(String userName) {
    	List<String> repos = loader.getUserToOwnerRepo().get(userName);
        return convertNamesToRepos(repos);
    }

    public List<RepoBriefVO> getUserSubscribeRepos(String userName) {
    	List<String> repos = loader.getUserToSubscribeRepo().get(userName);
    	return convertNamesToRepos(repos);
    }

    public List<RepoBriefVO> getUserCollaborateRepos(String userName) {
    	List<String> repos = loader.getUserToCollabRepo().get(userName);
    	
    	return convertNamesToRepos(repos);
    }

    public List<RepoBriefVO> getUserContributeRepos(String userName) {
    	List<String> repos = loader.getUserToContribute().get(userName);
    	return convertNamesToRepos(repos);
    }

}
