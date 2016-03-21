package org.GitIntegration.unit.api;

import java.util.List;

import edu.nju.git.data.api.liststring.RepositoriesListReader;
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
		RepositoriesListReader reader = new RepositoriesListReader();
		List<Object> list = reader.getNames();
		Assert.assertTrue( list.size()>=3216);
	}

}
