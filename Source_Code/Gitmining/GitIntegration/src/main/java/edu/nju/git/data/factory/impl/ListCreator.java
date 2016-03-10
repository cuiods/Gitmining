package edu.nju.git.data.factory.impl;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import edu.nju.git.VO.BranchVO;
import edu.nju.git.VO.CommitVO;
import edu.nju.git.VO.IssueVO;
import edu.nju.git.data.api.listdocument.BranchesReader;
import edu.nju.git.data.api.listdocument.CommitsReader;
import edu.nju.git.data.api.listdocument.Document;
import edu.nju.git.data.api.listdocument.IssuesReader;
import edu.nju.git.data.factory.impl.POfactory.SpecialBranchPOcreator;
import edu.nju.git.data.factory.impl.POfactory.SpecialCommitPOcreator;
import edu.nju.git.data.factory.impl.POfactory.SpecialIssuePOcreator;

public class ListCreator {

	public List<BranchVO> getBranches(String owner,String name){
		List<BranchVO> branchVOs = new ArrayList<BranchVO>();
		BranchesReader reader= new BranchesReader(owner,name);
		JsonNode node = reader.getNode();
		Document document = new Document();
		SpecialBranchPOcreator branchPOfactory = new SpecialBranchPOcreator(document);
		for (JsonNode jsonNode : node) {
			document.setJsonNode(jsonNode);
			
			branchVOs.add(branchPOfactory.getPO());
		}
		return branchVOs;
	}
	
	public List<CommitVO> getCommitVO(String owner,String name){
		List<CommitVO> returnlists = new ArrayList<CommitVO>();
		CommitsReader reader= new CommitsReader(owner,name);
		JsonNode node = reader.getNode();
		Document document = new Document();
		SpecialCommitPOcreator commitPOfactoryo = new SpecialCommitPOcreator(document);
		for (JsonNode jsonNode : node) {
			document.setJsonNode(jsonNode);
			returnlists.add(commitPOfactoryo.getPO());
		}
		return returnlists;
	}
	
	public List<IssueVO> getIssueVO(String owner,String name){
		List<IssueVO> returnlists = new ArrayList<IssueVO>();
		IssuesReader reader= new IssuesReader(owner,name);
		JsonNode node = reader.getNode();
		Document document = new Document();
		SpecialIssuePOcreator commitPOfactoryo = new SpecialIssuePOcreator(document);
		for (JsonNode jsonNode : node) {
			document.setJsonNode(jsonNode);
			returnlists.add(commitPOfactoryo.getPO());
		}
		return returnlists;
	}

}
