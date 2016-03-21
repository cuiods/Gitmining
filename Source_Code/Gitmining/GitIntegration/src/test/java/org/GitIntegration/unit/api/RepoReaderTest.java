package org.GitIntegration.unit.api;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.data.api.centralization.RepoMapReader;
import edu.nju.git.data.api.decentralization.RepoItemReader;
import edu.nju.git.data.factory.impl.gitminingCreator.RepoBriefPOfactory;
import junit.framework.TestCase;

public class RepoReaderTest extends TestCase{

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void test(){
		String owner = "vrpn";
		String repo = "vrpn";
		RepoBriefPOfactory repoBriefPOfactory = new RepoBriefPOfactory(new RepoMapReader(owner, repo));
		RepoBriefPOfactory repoBriefPOfactory2 = new RepoBriefPOfactory(new RepoItemReader(owner, repo));
		RepoBriefPO po1 = repoBriefPOfactory.getPO();
		RepoBriefPO po2 = repoBriefPOfactory2.getPO(); 
		assertEquals(po1.getDescription(),po2.getDescription());
		assertEquals(po1.getNum_subscribers(),po2.getNum_subscribers());
	}

}
