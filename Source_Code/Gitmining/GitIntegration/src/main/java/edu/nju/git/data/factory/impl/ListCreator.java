package edu.nju.git.data.factory.impl;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.VO.BranchVO;
import edu.nju.git.VO.CommitVO;
import edu.nju.git.VO.IssueVO;
import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.data.api.githubapi.BranchesReader;
import edu.nju.git.data.api.githubapi.CollaboratorsReader;
import edu.nju.git.data.api.githubapi.CommitsReader;
import edu.nju.git.data.api.githubapi.ContributorsReader;
import edu.nju.git.data.api.githubapi.Document;
import edu.nju.git.data.api.githubapi.ForksReader;
import edu.nju.git.data.api.githubapi.IssuesReader;
import edu.nju.git.data.factory.impl.githubCreator.GithubBranchPOcreator;
import edu.nju.git.data.factory.impl.githubCreator.GithubCommitPOcreator;
import edu.nju.git.data.factory.impl.githubCreator.GithubIssuePOcreator;
import edu.nju.git.data.factory.impl.gitminingCreator.RepoBriefPOfactory;
import edu.nju.git.data.factory.impl.gitminingCreator.UserBriefPOfactory;
import edu.nju.git.tools.POVOConverter;

public class ListCreator {

	private static ListCreator instance = null;
	private ListCreator(){}
	private static synchronized void createInstance(){
		if(instance==null){
			instance = new ListCreator();
		}
	}
	public static ListCreator getInstance(){
		if(instance==null){
			createInstance();
		}
		return instance;
	}
	
	public List<BranchVO> getBranches(String fullname){
		List<BranchVO> branchVOs = new ArrayList<BranchVO>();
		BranchesReader reader= new BranchesReader(fullname);
		JsonNode node = reader.getNode();
		Document document = new Document();
		GithubBranchPOcreator branchPOfactory = new GithubBranchPOcreator(document);
		for (JsonNode jsonNode : node) {
			document.setJsonNode(jsonNode);
			
			branchVOs.add(branchPOfactory.getPO());
		}
		return branchVOs;
	}
	public List<BranchVO> getBranches(String owner,String name){
		return this.getBranches(owner+"/"+name);
	}
	
	
	public List<CommitVO> getCommitVO(String fullname){
		List<CommitVO> returnlists = new ArrayList<CommitVO>();
		CommitsReader reader= new CommitsReader(fullname);
		JsonNode node = reader.getNode();
		Document document = new Document();
		GithubCommitPOcreator commitPOfactoryo = new GithubCommitPOcreator(document);
		for (JsonNode jsonNode : node) {
			document.setJsonNode(jsonNode);
			returnlists.add(commitPOfactoryo.getPO());
		}
		return returnlists;
	}
	public List<CommitVO> getCommitVO(String owner,String name){
		return this.getCommitVO(owner+"/"+name);
	}
	
	
	public List<IssueVO> getIssueVO(String fullname){
		List<IssueVO> returnlists = new ArrayList<IssueVO>();
		IssuesReader reader= new IssuesReader(fullname);
		JsonNode node = reader.getNode();
		Document document = new Document();
		GithubIssuePOcreator commitPOfactoryo = new GithubIssuePOcreator(document);
		for (JsonNode jsonNode : node) {
			document.setJsonNode(jsonNode);
			returnlists.add(commitPOfactoryo.getPO());
		}
		return returnlists;
	}
	public List<IssueVO> getIssueVO(String owner,String name){
		return this.getIssueVO(owner+"/"+name);
	}
	public List<UserBriefVO> getCollaborators(String fullname) {
		List<UserBriefVO> userBriefVOs = new ArrayList<UserBriefVO>();
		
		CollaboratorsReader collaboratorsReader = new CollaboratorsReader(fullname);
		JsonNode jsonNode = collaboratorsReader.getNode();
		Document document = new Document();
		UserBriefPOfactory userBriefPOfactory = new UserBriefPOfactory(document);
		for (JsonNode jsonNode2 : jsonNode) {
			document.setJsonNode(jsonNode2);
			UserBriefPO userBriefPO = userBriefPOfactory.getPO();
			userBriefVOs.add(POVOConverter.convert(userBriefPO));
		}
		return userBriefVOs;
	}
	public List<UserBriefVO> getContributors(String fullname) {

		List<UserBriefVO> userBriefVOs = new ArrayList<UserBriefVO>();
		
		ContributorsReader collaboratorsReader = new ContributorsReader(fullname);
		JsonNode jsonNode = collaboratorsReader.getNode();
		Document document = new Document();
		UserBriefPOfactory userBriefPOfactory = new UserBriefPOfactory(document);
		for (JsonNode jsonNode2 : jsonNode) {
			document.setJsonNode(jsonNode2);
			UserBriefPO userBriefPO = userBriefPOfactory.getPO();
			userBriefVOs.add(POVOConverter.convert(userBriefPO));
		}
		return userBriefVOs;
	}
	public List<RepoBriefVO> getForks(String fullname) {

		List<RepoBriefVO> userBriefVOs = new ArrayList<RepoBriefVO>();
		
		ForksReader collaboratorsReader = new ForksReader(fullname);
		JsonNode jsonNode = collaboratorsReader.getNode();
		Document document = new Document();
		RepoBriefPOfactory userBriefPOfactory = new RepoBriefPOfactory(document);
		for (JsonNode jsonNode2 : jsonNode) {
			document.setJsonNode(jsonNode2);
			RepoBriefPO userBriefPO = userBriefPOfactory.getPO();
			userBriefVOs.add(POVOConverter.convert(userBriefPO));
		}
		return userBriefVOs;
	}
	
	public List<UserBriefVO> getCollaborators(String owner,String name) {
		return this.getCollaborators(owner+"/"+name);
	}
	public List<UserBriefVO> getContributors(String owner,String name) {
		return this.getContributors(owner+"/"+name);
	}
	public List<RepoBriefVO> getForks(String owner,String name) {
		return this.getForks(owner+"/"+name);
	}

}
