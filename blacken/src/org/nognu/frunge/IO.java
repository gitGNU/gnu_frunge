package org.nognu.frunge;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

/**
 * Helper class for simple file input and output.
 * 
 * @author Dennis Heidsiek
 */
public class IO {
	
	protected final static List<String> PREFIXES = Arrays.asList("", "bin/", "out/");
	
	protected final static ClassLoader SCL = ClassLoader.getSystemClassLoader();


	/**
	 * @param s a relative link to a resource
	 * @return null in case of an error
	 */
	public static InputStream getStream(String s) {
		InputStream is = null;
		for(String p : PREFIXES) {
			is = SCL.getResourceAsStream(p.concat(s));
			if(is != null) {
				break;
			}
		}
		return is;
	}

	/**
	 * @param s a relative link to a UTF-8 encoded text file
	 * @return null in case of an error 
	 */
	public static BufferedReader getReader(String s) {
		BufferedReader r = null;
		try {
			r = new BufferedReader(new InputStreamReader(IO.getStream(s), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return r;
	}
	
}