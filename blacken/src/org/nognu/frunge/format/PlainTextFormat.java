package org.nognu.frunge.format;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.nognu.frunge.converter.Converter;

public class PlainTextFormat  implements Format {
	
	@Override
	public void process(Reader r, Writer w, Converter conv) throws IOException {
		char ch;
		StringBuilder sb = new StringBuilder();
		
		while((ch = (char) r.read()) != -1 && r.ready()) {
			if(Character.isLetter(ch)) {
				sb.append(ch);
			} else {
				if(sb.length() == 0) {
					w.write(ch);
				} else {
					w.append(conv.apply(sb.toString()));
					sb = new StringBuilder();
					w.write(ch);
				}
			}
		}
	}
	
}