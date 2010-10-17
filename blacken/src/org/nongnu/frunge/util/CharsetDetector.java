package org.nongnu.frunge.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;

import org.mozilla.intl.chardet.nsDetector;

public class CharsetDetector {
	
	public static BufferedReader asReader(InputStream is) {
		String charset = "UTF-8";
		try {
			if (is.markSupported()) {
				is.mark(Integer.MAX_VALUE);
				charset = CharsetDetector.guess(is);
				is.reset();
			} else {
				System.err.format("This stream doesn’t support mark!%n");
			}
		} catch (Exception e) {
			System.err.format("CharsetDetector is in big trouble.%n");
		}
		
		try {
			return Streams.asReader(is, charset);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String guess(InputStream is) {
		try {
			return analyse(new BufferedInputStream(is));
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
			System.out.format("%d. charset guess is: %s%n", (i + 1), prob[i]);
		}
		
		return prob[0];
	}
	
}
