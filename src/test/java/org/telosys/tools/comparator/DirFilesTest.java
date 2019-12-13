package org.telosys.tools.comparator;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.telosys.tools.commons.DirUtil;

import junit.tests.util.TestsEnv;

public class DirFilesTest {

	@Test
	public void test_aaa() {
		List<String> files = DirUtil.getDirectoryFiles(new File(TestsEnv.SRC_TEST_RESOURCES) , true);
//		logDebug(files.size() + " file(s) found in the directory ");
//		logDebug("Comparison...");
		for ( String f : files ) {
			System.out.println(" . " + f);
		}
	}
}
