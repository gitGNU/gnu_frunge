package org.nongnu.frunge.format;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.nongnu.frunge.converter.Converter;

public class Formats {
	
	public static void process(Format f, Reader r, Writer w, Converter c) {
		try {
			f.process(r, w, c);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				r.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				w.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
