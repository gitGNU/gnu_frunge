package org.nongnu.frunge.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;

import org.mozilla.intl.chardet.nsDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharsetDetector {
	
	final static Logger log = LoggerFactory.getLogger(CharsetDetector.class);
	
	public static BufferedReader asReader(InputStream is) {
		String charset = "UTF-8";
		try {
			if (is.markSupported()) {
				is.mark(Integer.MAX_VALUE);
				charset = CharsetDetector.guess(is);
				is.reset();
			} else {
				log.error("This stream doesn’t support mark!");
			}
		} catch (Exception e) {
			log.error("CharsetDetector is in big trouble.", e);
		}
		
		try {
			return Streams.asReader(is, charset);
		} catch (Exception e) {
			log.error("Can’t open Resource", e);
			return null;
		}
	}
	
	public static String guess(InputStream is) {
		try {
			return CharsetDetector.analyse(new BufferedInputStream(is));
		} catch (Exception e) {
			return "UNKNOWN FILE ENCODING!";
		}
	}
	
	protected static String analyse(BufferedInputStream imp) throws Exception {
		nsDetector det = new nsDetector();
		
		byte[] buf = new byte[1024];
		int len;
		
		boolean done = false;
		boolean isAscii = true;
		
		while ((len = imp.read(buf, 0, buf.length)) != -1) {
			// Check if the stream is only ascii.
			if (isAscii) {
				isAscii = det.isAscii(buf, len);
			}
			// DoIt if non-ascii and not done yet.
			if (!isAscii && !done) {
				done = det.HandleData(buf, len);
			}
		}
		det.DataEnd();
		
		if (isAscii) {
			return "ASCII";
		}
		
		String prob[] = det.getProbableCharsets();
		for (int i = 0; i < prob.length; i++) {
			log.debug("{}. charset guess is: {}%n", (i + 1), prob[i]);
		}
		
		return prob[0];
	}
	
}
