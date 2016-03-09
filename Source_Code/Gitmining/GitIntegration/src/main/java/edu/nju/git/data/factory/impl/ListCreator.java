package edu.nju.git.data.factory.impl;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import edu.nju.git.VO.BranchVO;
import edu.nju.git.data.api.listdocument.BranchesListReader;
import edu.nju.git.data.api.listdocument.Document;
import edu.nju.git.data.api.listdocument.ListDocumentReader;
import edu.nju.git.data.factory.impl.POfactory.BranchPOfactory;
import edu.nju.git.data.factory.service.POfactory;

public class ListCreator {

	public List<BranchVO> getBranches(String owner,String name){
		List<BranchVO> branchVOs = new ArrayList<BranchVO>();
		BranchesListReader reader= new BranchesListReader(owner,name);
		JsonNode node = reader.getNode();
		Document document = new Document();
		BranchPOfactory branchPOfactory = new BranchPOfactory(document);
		for (JsonNode jsonNode : node) {
			document.setJsonNode(jsonNode);
			
			branchVOs.add(branchPOfactory.getPO());
		}
		return branchVOs;
	}
	
	public List<BranchVO> getTBranches(String owner,String name){
		Document document = new Document();
		return this.getT(new BranchesListReader(owner,name), document, new BranchPOfactory(document));
	}
	
	private <T> List<T> getT(ListDocumentReader reader,Document document,POfactory<T> POfactory){
		List<T> branchVOs = new ArrayList<T>();
		JsonNode node = reader.getNode();
		for (JsonNode jsonNode : node) {
			document.setJsonNode(jsonNode);
			POfactory.getPO();
		}
		return branchVOs;
	}
}
