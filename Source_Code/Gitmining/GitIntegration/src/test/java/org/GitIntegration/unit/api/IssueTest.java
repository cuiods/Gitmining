package org.GitIntegration.unit.api;

import java.util.List;

import edu.nju.git.data.api.decentralization.IssueItemReader;
import edu.nju.git.data.api.liststring.IssuesListReader;
import edu.nju.git.data.factory.impl.POfactory.IssuePOfactory;
import junit.framework.TestCase;

public class IssueTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testIssueReader(){
		String fullname = "vrpn/vrpn";
		
		List<Object> issues = new IssuesListReader(fullname).getNames();
		for (Object object : issues) {
			System.out.println(object);
		}
		IssueItemReader issueItemReader = new IssueItemReader();
		IssuePOfactory issuePOfactory = new IssuePOfactory();
		issuePOfactory.setItemHelper(issueItemReader);
		if(issues.size()>0) {
			String string = issues.get(2).toString();
			issueItemReader.setNames(fullname, string);
			issuePOfactory.setSHA(string);
			System.out.println(string+"  "+issuePOfactory.getPO().getCreate_at());
		}else{
			System.out.println("null");
		}
		
	}
}
