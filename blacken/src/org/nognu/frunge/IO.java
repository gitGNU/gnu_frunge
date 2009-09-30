package org.nognu.frunge;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Helper class for simple file input and output.
 * 
 * @author Dennis Heidsiek
 */
public class IO {
	
	protected static List<String> PREFIXES = Arrays.asList("", "bin/", "out/");

	protected static InputStream openStream(String s) {
		return ClassLoader.getSystemClassLoader().getResourceAsStream(s);
	}

	public static InputStream getStream(String s) {
		InputStream is = null;
		for(String p : PREFIXES) {
			is = openStream(p.concat(s));
			if(is != null) {
				break;
			}
		}
		return is;
	}
	
}