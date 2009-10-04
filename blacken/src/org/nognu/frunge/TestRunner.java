package org.nognu.frunge;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import org.nognu.frunge.hyphen.Patterns;


/**
 * Executes some nasty s or ſ tests.
 * 
 * @author Dennis Heidsiek
 */
public class TestRunner {

	protected final static List<String> LANGUAGES = Arrays.asList("de", "en");
	
	public TestRunner() {
		for(String lang : LANGUAGES) {
			System.out.format("Run test cases for language %s:%n", lang);
			Patterns p = new Patterns(lang);
			//System.out.format("%s%n", p);
			
			try {
				BufferedReader r = IO.getReader("testcases/"+lang+".csv");

				String line;
				while ((line = r.readLine()) != null) {
					int pos = line.indexOf(";");
					String in = line.substring(0, pos);
					String out = line.substring(pos + 1, line.length());

					System.out.format("%s -> %s%n", in, out);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.format("%n");
		}
	}

}