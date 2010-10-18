package org.nongnu.frunge.format;

import java.io.IOException;

import org.nongnu.frunge.converter.Converter;

public interface Format {
	
	public void process(Readable r, Appendable w, Converter conv)
			throws IOException;
	
}
