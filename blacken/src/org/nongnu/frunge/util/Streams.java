package org.nongnu.frunge.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * Helper class for simple file input and output.
 * 
 * @author Dennis Heidsiek
 */
public class Streams {
	
	/**
	 * @return null in case of an error
	 */
	public static BufferedReader asReader(InputStream is, String charset) {
		BufferedReader r = null;
		try {
			r = new BufferedReader(new InputStreamReader(is, charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public static BufferedWriter asWriter(OutputStream os, String charset) {
		BufferedWriter w = null;
		try {
			w = new BufferedWriter(new OutputStreamWriter(os, charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return w;
	}
	
	/**
	 * @param s
	 *          a relative link to a UTF-8 encoded text file
	 * @return null in case of an error
	 */
	public static BufferedReader getReader(String s) {
		return Streams.asReader(Resources.getStream(s), "UTF-8");
	}
	
	public static BufferedWriter getWriter(String s) {
		BufferedWriter w = null;
		try {
			w = Streams.asWriter(new FileOutputStream(new File(s)), "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return w;
	}
}
