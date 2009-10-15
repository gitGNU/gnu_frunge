package org.nognu.frunge.format;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.nognu.frunge.converter.Converter;


public interface Format {

	public void process(Reader r, Writer w, Converter conv) throws IOException;
	
}