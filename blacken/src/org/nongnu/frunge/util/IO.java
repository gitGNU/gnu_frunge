package org.nongnu.frunge.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
		if(is == null) {
			try {
				is = new FileInputStream(new File(s));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return is;
	}

	public static BufferedReader asUTF8(InputStream is) {
		BufferedReader r = null;
		try {
			r = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public static BufferedWriter asUTF8(OutputStream os) {
		BufferedWriter w = null;
		try {
			w = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return w;
	}

	/**
	 * @param s a relative link to a UTF-8 encoded text file
	 * @return null in case of an error 
	 */
	public static BufferedReader getReader(String s) {
		return IO.asUTF8(IO.getStream(s));
	}

	public static BufferedWriter getWriter(String s) {
		BufferedWriter w = null;
		try {
			w = IO.asUTF8(new FileOutputStream(new File(s)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return w;
	}
}
