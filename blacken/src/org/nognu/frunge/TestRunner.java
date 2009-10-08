package org.nognu.frunge;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import org.nognu.frunge.converter.Converter;
import org.nognu.frunge.converter.Converters;
import org.nognu.frunge.hyphen.Patterns;


/**
 * Executes some nasty s or ſ tests.
 * 
 * @author Dennis Heidsiek
 */
public class TestRunner {

	protected final static List<String> LANGUAGES = Arrays.asList("de", "en");
	
	public TestRunner(boolean verbose) {
		for(String lang : LANGUAGES) {
			System.out.format("Run test cases for language (%s):%n", lang);
			Metric m = new Metric();
			final Patterns p = new Patterns(lang, verbose);		
			Converter f = Converters.simpleOne();
			
			System.out.format("Using %s%n", verbose ? p : p.toString());
			if(verbose) System.out.format("All incorrect transformed testcases:%n");		
			
			try {
				BufferedReader r = IO.getReader("testcases/"+lang+".csv");

				String line;
				while ((line = r.readLine()) != null) {
					int pos = line.indexOf(";");
					String key = line.substring(0, pos);
					String expected = line.substring(pos + 1, line.length());
					String actual = f.apply(key);
					m.addCase(expected, actual);
					
					if(verbose && !expected.equals(actual)) {
						System.out.format("%s -> %s: %s%n", key, expected, actual);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.format("%s%n%n", m);
		}
	}

}