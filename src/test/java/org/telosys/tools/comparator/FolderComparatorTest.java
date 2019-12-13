package org.telosys.tools.comparator;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.telosys.tools.commons.FileUtil;

import static org.junit.Assert.assertEquals;

import junit.tests.util.TestsEnv;

public class FolderComparatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	private File getFolder1() {
		return new File(TestsEnv.SRC_TEST_RESOURCES+ "folder1");
	}
	private File getFolder2() {
		return new File(TestsEnv.SRC_TEST_RESOURCES+ "folder2");
	}
	
	private FolderComparator getFolderComparator() {
		LineComparatorForGeneratedFiles lineComparator = new LineComparatorForGeneratedFiles();
		FileComparator fileComparator = new FileComparator( lineComparator );
		return new FolderComparator(fileComparator);
	}

	@Test
	public void test1() {
		FolderComparator fc = getFolderComparator();
		List<FolderFilesPair> pairs = fc.getFilesToCompare(getFolder1(), getFolder2());
		for ( FolderFilesPair pair : pairs) {
			System.out.println(" ");
			System.out.println(" Folder 1 : " + pair.getFolder1Path() );
			System.out.println(" Folder 2 : " + pair.getFolder2Path() );
			System.out.println(" File 1   : " + pair.getFile1() );
			System.out.println(" File 2   : " + pair.getFile2() );
			System.out.println(" File     : " + pair.getFileRelativePath() );
			
			File f1 = new File(FileUtil.buildFilePath(pair.getFolder1Path(), pair.getFileRelativePath()));
			assertEquals(f1.getAbsolutePath(), pair.getFile1().getAbsolutePath());

			File f2 = new File(FileUtil.buildFilePath(pair.getFolder2Path(), pair.getFileRelativePath()));
			assertEquals(f2.getAbsolutePath(), pair.getFile2().getAbsolutePath());
		}
	}

}
