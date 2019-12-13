package org.telosys.tools.comparator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.telosys.tools.comparator.LineComparator;
import org.telosys.tools.comparator.LineComparatorForGeneratedFiles;

import static org.junit.Assert.*;

public class LineComparatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1() {
		String line1 = " * Created on 3 sept. 2015 ( Time 15:45:42 )" ;
		String line2 = " * Created on 3 sept. 2015 ( Time 18:53:55 )" ;

		LineComparator lc = new LineComparatorForGeneratedFiles();
		boolean r = lc.consideredAsIdentical(line1, line2);
		assertTrue(r);
	}

	@Test
	public void test2() {
		String line1 = " Integer i ;" ;
		String line2 = " int i ;" ;

		LineComparator lc = new LineComparatorForGeneratedFiles();
		boolean r = lc.consideredAsIdentical(line1, line2);
		assertFalse(r);
	}

}
