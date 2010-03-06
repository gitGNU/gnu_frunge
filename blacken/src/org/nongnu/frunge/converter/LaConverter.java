package org.nongnu.frunge.converter;

/**
 * A very simple {@link Converter} for the Latin language.
 * 
 * <p>Still to reſolve:
 * <a href="http://de.wikipedia.org/wiki/Diskussion:%C3%9F#.C3.9F_in_Lateinischer_Sprache.3F">ß in Latin</a>,
 * <a href="http://de.wikipedia.org/wiki/Datei:Trithemius-Polygraphiae-71.jpg">related image</a>.</p>
 * 
 */
public class LaConverter implements Converter {
	
	@Override
	public String apply(String in) {
		String out = in.replace('s', 'ſ').substring(0, in.length()-1);
		return out + in.charAt(in.length()-1);
	}
	
}
