package org.nongnu.frunge.format;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.nongnu.frunge.converter.Converter;


public interface Format {

	public void process(Reader r, Writer w, Converter conv) throws IOException;
	
}