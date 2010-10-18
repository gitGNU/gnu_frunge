package org.nongnu.frunge.format;

import java.io.IOException;
import java.util.Scanner;

import org.nongnu.frunge.converter.Converter;

public class PlainTextFormat implements Format {
	
	@Override
	public void process(Readable r, Appendable w, Converter conv)
			throws IOException {
		Scanner scanner = new Scanner(r);
		
		while (scanner.hasNext()) {
			String s = scanner.next();
			w.append(conv.apply(s));
			w.append("|");
		}
		
		/*
		 * char ch; StringBuilder sb = new StringBuilder(); while ((ch = (char)
		 * r.read()) != -1 && r.ready()) { if (Character.isLetter(ch)) {
		 * sb.append(ch); } else { if (sb.length() == 0) { w.write(ch); } else {
		 * w.append(conv.apply(sb.toString())); sb = new StringBuilder();
		 * w.write(ch); } } }
		 */

	}
	
}
