package org.nongnu.frunge.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Helper class for reading resources.
 * 
 * @author Dennis Heidsiek
 */
public class Resources {
	
	protected final static String[] PREFIXES = new String[] { "", "bin/", "out/" };
	
	protected final static ClassLoader SCL = ClassLoader.getSystemClassLoader();
	
	/**
	 * @param s
	 *          a relative link to a resource
	 * @return null in case of an error
	 */
	public static InputStream getStream(String s) {
		InputStream is = null;
		for (String p : Resources.PREFIXES) {
			is = Resources.SCL.getResourceAsStream(p.concat(s));
			if (is != null) {
				break;
			}
		}
		if (is == null) {
			try {
				is = new FileInputStream(new File(s));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return is;
	}
	
}
