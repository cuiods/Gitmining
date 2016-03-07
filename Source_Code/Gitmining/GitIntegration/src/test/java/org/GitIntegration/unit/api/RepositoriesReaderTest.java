package org.GitIntegration.unit.api;

import java.util.List;

import edu.nju.git.data.api.RepositoriesReader;
import junit.framework.Assert;
import junit.framework.TestCase;

public class RepositoriesReaderTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testFullNames() {
		RepositoriesReader reader = new RepositoriesReader();
		List<String> list = reader.getNames();
		for (String string : list) {
			System.out.println(string);
		}
		Assert.assertTrue( list.size()>1000);
	}

}
