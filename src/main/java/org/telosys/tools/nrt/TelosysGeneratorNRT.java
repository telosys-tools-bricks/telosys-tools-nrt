package org.telosys.tools.nrt;

import java.io.File;
import java.util.List;

import org.telosys.tools.api.TelosysProject;
import org.telosys.tools.commons.DirUtil;
import org.telosys.tools.commons.FileUtil;
import org.telosys.tools.commons.TelosysToolsException;
import org.telosys.tools.commons.cfg.TelosysToolsCfg;
import org.telosys.tools.comparator.ComparisonResult;
import org.telosys.tools.comparator.FileComparator;
import org.telosys.tools.comparator.LineComparatorForGeneratedFiles;
import org.telosys.tools.generator.task.GenerationTaskResult;
import org.telosys.tools.generic.model.Model;

public class TelosysGeneratorNRT {

	private final String generatedFilesSubFolder ;
	
	public TelosysGeneratorNRT(String generatedFilesSubFolder) {
		super();
		this.generatedFilesSubFolder = generatedFilesSubFolder;
	}

	private void logDebug(String msg) {
		System.out.println("[DEBUG] " + msg);
	}
	private void logInfo(String msg) {
		System.out.println(msg);
	}
	
	public int generateAndCompare(TelosysProject telosysProject, String modelName, String bundleName) 
			throws TelosysToolsException {
		int differencesCount = 0 ;
		logInfo("=======================================================================================");
		logInfo("Generate and compare : model '" + modelName + "', bundle '" + bundleName +"'");
		logDebug("Updating destination folder...");
		TelosysToolsCfg telosysToolsCfg = telosysProject.loadTelosysToolsCfg();
		telosysToolsCfg.setSpecificDestinationFolder(getDestinationFolderAbsolutePath(telosysProject));
		telosysProject.saveTelosysToolsCfg(telosysToolsCfg);
		logDebug("Update done : " + telosysToolsCfg.getDestinationFolderAbsolutePath() );
		
		File destinationFolder = new File(telosysToolsCfg.getDestinationFolderAbsolutePath());
		logDebug("Cleaning destination folder...");
		DirUtil.deleteDirectory( destinationFolder );
		logDebug("Cleaning done.");
		
		logDebug("Loadind model...");
		Model model = telosysProject.loadModel(modelName);
		logDebug("Model loaded : " + model.getEntities().size() + " entities");
		
		logDebug("Launching generation for bundle ");
		GenerationTaskResult genResult = telosysProject.launchGeneration(model, bundleName);
		if ( genResult.getNumberOfGenerationErrors() > 0 ) {
			throw new RuntimeException("ERROR DURING GENERATION TASK : " + genResult.getNumberOfGenerationErrors() );
		}
		logDebug("Normal end of generation : " + genResult.getNumberOfFilesGenerated() + " files generated");
		
		List<String> files = DirUtil.getDirectoryFiles(destinationFolder, true);
		logDebug(files.size() + " file(s) found in the directory ");
		logDebug("Comparison...");
		for ( String generatedFileName : files ) {
			logDebug("----- ");
			logDebug(" . " + generatedFileName );
			String relativeFileName = generatedFileName.substring( telosysToolsCfg.getDestinationFolderAbsolutePath().length() + 1 );
			logDebug("   ( " + relativeFileName + " )");
			String referenceFileName = FileUtil.buildFilePath(telosysToolsCfg.getProjectAbsolutePath(), relativeFileName);
			logDebug(" . " + referenceFileName );
			StringBuilder sb = new StringBuilder();
			ComparisonResult result = compare(referenceFileName, generatedFileName) ;
			int differences = result.getDiffCount();
			if ( differences != 0 ) {
				logInfo(" . " + generatedFileName + " : " + differences + " difference(s)");
				logInfo(sb.toString());
			}
			else {
				logInfo(" . " + generatedFileName + " : no difference ");
			}
			differencesCount = differencesCount + differences ;
		}
		return differencesCount ;
	}
	
	private String getDestinationFolderAbsolutePath(TelosysProject telosysProject) {
		return FileUtil.buildFilePath(telosysProject.getProjectFolder(), generatedFilesSubFolder);
	}

	private ComparisonResult compare(String refFileName, String newFileName) {
		LineComparatorForGeneratedFiles lineComparator = new LineComparatorForGeneratedFiles();
//		FileComparator fileComparator = new FileComparator(
//				new File(refFileName), new File(newFileName), lineComparator);
		FileComparator fileComparator = new FileComparator(lineComparator);
		ComparisonResult result = fileComparator.compare(new File(refFileName), new File(newFileName)) ;
		if ( result.getDiffCount() != 0 ) {
			logDebug("DIFFERENT : ");
		}
		else {
			logDebug("INDENTICAL");
		}
		return result ;
	}
}
