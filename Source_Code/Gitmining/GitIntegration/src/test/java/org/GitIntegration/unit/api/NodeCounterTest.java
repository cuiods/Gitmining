package org.GitIntegration.unit.api;

import java.net.MalformedURLException;

import edu.nju.git.data.api.NodeCounter;
import junit.framework.TestCase;

public class NodeCounterTest extends TestCase {

	NodeCounter nodeCounter ;
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testNodeCounter1() throws MalformedURLException {
		nodeCounter = new NodeCounter("https://api.github.com/repos/huerlisi/CyDoc/contributors");
		assertEquals(nodeCounter.count(), 4);
	}
	public void testNodeCounter2() throws MalformedURLException {
		nodeCounter = new NodeCounter("https://api.github.com/repos/huerlisi/CyDoc/contributors");
		assertEquals(nodeCounter.count_subson(0), 18);
	}
	public void testNodeCounter3() throws MalformedURLException {
		nodeCounter = new NodeCounter("https://api.github.com/users/huerlisi/followers");
		assertEquals(nodeCounter.count(), 30);
	}

}
