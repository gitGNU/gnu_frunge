package org.nognu.frunge;

import java.io.*;


/**
 * Executes some nasty s or ſ tests.
 * 
 * @author Dennis Heidsiek
 */
public class TestRunner {

	public TestRunner() {
		System.out.format("Run tests:%n");
		
		try {
			BufferedReader r = IO.getReader("testcases/de.csv");

			String line;
			while ((line = r.readLine()) != null) {
				int pos = line.indexOf(";");
				String in = line.substring(0, pos);
				String out = line.substring(pos + 1, line.length());

				System.out.format("Testcase: %s -> %s%n", in, out);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}