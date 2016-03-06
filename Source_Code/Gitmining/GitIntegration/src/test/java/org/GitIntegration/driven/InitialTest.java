package org.GitIntegration.driven;

import edu.nju.git.data.init.impl.Initial;
import junit.framework.TestCase;

public class InitialTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testExcute() {
		new Initial().excute();
	}

}
