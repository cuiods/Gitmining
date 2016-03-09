package edu.nju.git.data.api.centralization;

import edu.nju.git.data.api.abstractservice.AbstractMapGetter;
/**
 * json example:
 * <br/>"name": "staging",
 * <br/>"commit": {
 * <br/>  "sha": "c8ef2a534a6b535ceef02f259a6123d8821e70c7",
 * <br/>  "url": "https://api.github.com/repos/huerlisi/CyDoc/commits/c8ef2a534a6b535ceef02f259a6123d8821e70c7"
 * <br/>},
 * <br/>"fn": "huerlisi/CyDoc"
 * @deprecated
 * @author daixinyan
 * @date 2016-03-09
 */
public class BrancheMapReader extends AbstractMapGetter {

	private String fullname;
	private String branchname;
	
	public BrancheMapReader(String fullnames,String branchname) {
		this.setNames(fullnames, branchname);
	}

	private void setNames(String fullnames,String branchname){
		this.fullname = fullnames;
		this.branchname = branchname;
		this.init();
	}
	
	@Override
	protected String getUrl() {
		return "http://www.gitmining.net/api/repository/"+fullname+"/branch/"+branchname;
	}

}
