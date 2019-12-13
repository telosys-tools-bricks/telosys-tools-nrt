package org.telosys.tools.comparator;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.telosys.tools.comparator.FileComparator;
import org.telosys.tools.comparator.LineComparatorForGeneratedFiles;

import static org.junit.Assert.*;

import junit.tests.util.TestsEnv;

public class FileComparatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	private File getResourceFile(String fileName) {
		return new File(TestsEnv.SRC_TEST_RESOURCES + "files/" + fileName );
	}
	
	private void compare(String fileName1, String fileName2, int nbDiff ) {
		System.out.println("-----------------------------" );
		System.out.println("Comparing " + fileName1 + " and " + fileName2 );
		
		LineComparatorForGeneratedFiles lineComparator = new LineComparatorForGeneratedFiles();
//		FileComparator fc = new FileComparator( getResourceFile(fileName1) , getResourceFile(fileName2), lineComparator );
		FileComparator fc = new FileComparator( lineComparator );

//		StringBuilder sb = new StringBuilder();
		File file1 = getResourceFile(fileName1);
		File file2 = getResourceFile(fileName2);
		ComparisonResult result = fc.compare(file1, file2);
		System.out.println(result.getDiffCount() == 0 ? "Identical" : "Different" ) ;
		System.out.println(result.getReport());
		assertEquals(nbDiff, result.getDiffCount());
	}

	@Test
	public void test_aaa() {
		compare("aaa1.txt", "aaa2.txt", 0);
	}
	@Test
	public void test_bbb() {
		compare("bbb1.txt", "bbb2.txt", 1);
	}
	@Test
	public void test_ccc() {
		compare("ccc1.txt", "ccc2.txt", 1);
	}
	@Test
	public void test_ddd() {
		compare("ddd1.txt", "ddd2.txt", 1);
	}
	@Test
	public void test_AuthorRecord() {
		compare("AuthorRecord1.java", "AuthorRecord2.java", 0);
	}

}
