package org.telosys.tools.comparator;

import java.io.File;

import org.telosys.tools.commons.FileUtil;

public class FolderFilesPair {

	private final String folder1FullPath ;
	private final String folder2FullPath ;
	private final String file1FullPath ;
	private final String file2FullPath ;
	private final String fileRelativePath ;
	
	public FolderFilesPair(String folder1FullPath, String file1FullPath, String folder2FullPath) {
		super();
		this.folder1FullPath = folder1FullPath;
		this.file1FullPath = file1FullPath;
		this.folder2FullPath = folder2FullPath;

		this.fileRelativePath = file1FullPath.substring( folder1FullPath.length() + 1 );
		this.file2FullPath = FileUtil.buildFilePath(folder2FullPath, fileRelativePath);
	}

	public File getFile1() {
		return new File(file1FullPath);
	}

	public File getFile2() {
		return new File(file2FullPath);
	}

	public String getFileRelativePath() {
		return fileRelativePath;
	}

	public String getFolder1Path() {
		return folder1FullPath;
	}
	
	public String getFolder2Path() {
		return folder2FullPath;
	}
}
