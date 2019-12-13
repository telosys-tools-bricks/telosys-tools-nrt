package org.telosys.tools.comparator;

public class ComparisonResult {

	private final int numberOfDifferencesFound ;
	
	private final String report ;
	
	public ComparisonResult(int numberOfDifferencesFound, String report) {
		super();
		this.numberOfDifferencesFound = numberOfDifferencesFound;
		this.report = report;
	}

	/**
	 * Returns the number of differences found
	 * @return
	 */
	public int getDiffCount() {
		return numberOfDifferencesFound;
	}

	/**
	 * Returns the comparison report 
	 * @return
	 */
	public String getReport() {
		return report;
	}

}
