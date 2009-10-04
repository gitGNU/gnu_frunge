package org.nognu.frunge;

import java.util.Formattable;
import java.util.Formatter;


public class Metric implements Formattable {

	protected int totalCases;
	
	protected int correctCases;
		
	public Metric() {
		super();
		this.totalCases = 0;
		this.correctCases = 0;
	}
	
	public void addCase(String expected, String actual) {
		this.totalCases++;
		if(actual.equals(expected)) {
			this.correctCases++;
		}
	}

	@Override
	public void formatTo(Formatter f, int flags, int width, int precision) {
		f.format("Result Metric (%.2f%% correct: %d of %d)",
				(double) (100*this.correctCases)/this.totalCases,
				this.correctCases,
				this.totalCases
				);
	}
}
