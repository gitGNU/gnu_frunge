package org.nongnu.frunge.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class for reading resources.
 * 
 * @author Dennis Heidsiek
 */
public class Resources {
	
	protected final static String[] PREFIXES = new String[] { "", "bin/", "out/" };
	
	protected final static ClassLoader SCL = ClassLoader.getSystemClassLoader();

	final static Logger log = LoggerFactory.getLogger(Resources.class);
	
	/**
	 * @param res
	 *          a relative link to a resource
	 * @return null in case of an error
	 */
	public static InputStream getStream(String res) {
		InputStream is = null;
		for (String p : Resources.PREFIXES) {
			is = Resources.SCL.getResourceAsStream(p.concat(res));
			if (is != null) {
				break;
			}
		}
		if (is == null) {
			try {
				is = new FileInputStream(new File(res));
			} catch (FileNotFoundException e) {
				log.error("Can’t open file", e);
			}
		}
		return is;
	}
	
	public static InputStream getStream(String res, String jarPattern) {
		Pattern p = Pattern.compile(jarPattern);
		InputStream is = null;
		try {
			for (URL u : Collections.list(Resources.SCL.getResources(res))) {
				if (p.matcher(u.toString()).matches()) {
					log.debug("Reading stream from {}", u);
					is = u.openStream();
					break;
				}
			}
		} catch (IOException e) {
			log.error("Can’t open tesources", e);
		}
		return is;
	}
	
}
