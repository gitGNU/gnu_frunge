package org.nongnu.frunge.core;

import java.io.BufferedReader;

import org.nongnu.frunge.converter.Converter;
import org.nongnu.frunge.converter.Converters;
import org.nongnu.frunge.hyphen.PatternSet;
import org.nongnu.frunge.util.Streams;

/**
 * Executes some nasty s or ſ tests.
 * 
 * @author Dennis Heidsiek
 */
public class TestRunner {
	
	public TestRunner(String lang, boolean verbose) {
		this.runTest(lang, verbose);
	}
	
	public TestRunner(boolean verbose) {
		for (String lang : Converters.supportedLanguages()) {
			this.runTest(lang, verbose);
		}
	}
	
	protected void runTest(String lang, boolean verbose) {
		System.out.format("Run test cases for language (%s):%n", lang);
		Metric m = new Metric();
		final PatternSet p = new PatternSet(lang);
		Converter f = Converters.get(lang);
		
		System.out.format("Using %s%n", p.toString());
		if (verbose) {
			System.out.format("All incorrect transformed testcases:%n");
		}
		
		try {
			BufferedReader r = Streams.getReader("testcases/" + lang + ".csv");
			
			String line;
			while ((line = r.readLine()) != null) {
				int pos = line.indexOf(";");
				String key = line.substring(0, pos);
				String expected = line.substring(pos + 1, line.length());
				String actual = f.apply(key);
				m.addCase(expected, actual);
				
				if (verbose) {// && !expected.equals(actual)) {
					System.out.format("%s -> %s: %s (%s)%n", key, expected, actual,
							p.apply(key));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.format("%s%n%n", m);
	}
	
}
