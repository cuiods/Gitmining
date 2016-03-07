package edu.nju.git.data.api.centralization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.nju.git.PO.BranchPO;
import edu.nju.git.PO.CommitPO;
import edu.nju.git.PO.IssuePO;
import edu.nju.git.data.factory.impl.MapPO.MapBranchPOfactory;
import edu.nju.git.data.factory.impl.MapPO.MapCommitPOfactory;
import edu.nju.git.data.factory.impl.MapPO.MapIssuePOfactory;
import edu.nju.git.data.factory.service.MapPOfactory;
/**
 * only usable for whose url looks like :
 *  "http://www.gitmining.net/api/repository/{owner}/{reponame}/{parameter}"
 *  <br/>for example:
 *  "http://www.gitmining.net/api/repository/{owner}/{reponame}/collaborators"
 * @author daixinyan
 * @date 2016-03-07
 * @param <T> usually PO
 */
public class ListRepoReader<T>  {

	private ListReader<T> listReader;
	private static final String urlString = "http://www.gitmining.net/api/repository/";
	private  static Map<Class<?>,MapPOfactory<?>> factoryMap;
	private static Map<Class<?>, String> urlMap ;
	
	private ListRepoReader()
	{
		if(factoryMap==null)
		{
			this.createFacrotyMap();
		}
		if(urlMap==null)
		{
			this.createUrlMap();
		}
	}
	/**
	 * @param cla 
	 * @param fullname like: {owner}/{name}
	 */
	@SuppressWarnings("unchecked")
	public ListRepoReader(Class<T> cla,String fullname)
	{
		this();
		listReader = new ListReader<>((MapPOfactory<T>)factoryMap.get(cla),urlString+fullname+"/"+urlMap.get(cla));
	}
	
	@SuppressWarnings("unchecked")
	public ListRepoReader(Class<T> cla,String owner,String repoName)
	{
		this();
		listReader = new ListReader<>((MapPOfactory<T>)factoryMap.get(cla),urlString+owner+"/"+repoName+"/"+urlMap.get(cla));
	}
	
	public  List<T> getPOList()
	{
		return listReader.getPOList();
	}
	

	private synchronized void createFacrotyMap()
	{
		if(factoryMap==null)
		{
			factoryMap=new HashMap<Class<?>, MapPOfactory<?>>();
			//factoryMap.put(UserPO.class, new UserPOfactory());
			//factoryMap.put(UserBriefPO.class, new UserBriefPOfactory());
			factoryMap.put(BranchPO.class, new MapBranchPOfactory());
			factoryMap.put(CommitPO.class, new MapCommitPOfactory());
			factoryMap.put(IssuePO.class, new MapIssuePOfactory());
			//factoryMap.put(RepoBriefPO.class, new RepoBriefPOfactory());
			//factoryMap.put(RepoPO.class, new RepoPOfactory());
		}
	}
	private synchronized void createUrlMap()
	{
		if(urlMap==null)
		{
			urlMap = new HashMap<Class<?>, String>();
//languages
//item?
//contributors
//collaborators

//forks
			//branches
			//issues
			//commits
			//
			//urlMap.put(UserPO.class, "");
			//urlMap.put(UserBriefPO.class, "");
			urlMap.put(BranchPO.class, "branches");
			urlMap.put(CommitPO.class, "commits");
			urlMap.put(IssuePO.class, "issues");
			//urlMap.put(RepoBriefPO.class, "");
			//urlMap.put(RepoPO.class, "");
		}
	}
}
