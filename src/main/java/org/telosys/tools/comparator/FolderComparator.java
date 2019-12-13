package org.telosys.tools.comparator;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.telosys.tools.commons.DirUtil;

public class FolderComparator {

	private final FileComparator fileComparator ;
	
	public FolderComparator( FileComparator fileComparator ) {
		this.fileComparator = fileComparator ;
	}
	
	public List<FolderFilesPair> getFilesToCompare(File folder1, File folder2) {
		List<String> files = DirUtil.getDirectoryFiles(folder1, true);
//		logDebug(files.size() + " file(s) found in the directory ");
//		logDebug("Comparison...");
		List<FolderFilesPair> pairs = new LinkedList<>();
		for ( String file1 : files ) {
			FolderFilesPair pair = new FolderFilesPair( folder1.getAbsolutePath(), file1, folder2.getAbsolutePath());
			pairs.add(pair);
		}
		return pairs;
	}
	
//	private ComparisonResult compareFiles(String fileName1, String fileName2) {
//		ComparisonResult result = fileComparator.compare(new File(fileName1), new File(fileName2)) ;
//		if ( result.getDiffCount() != 0 ) {
//			logDebug("DIFFERENT : ");
//		}
//		else {
//			logDebug("INDENTICAL");
//		}
//		return result ;
//	}
	
}
