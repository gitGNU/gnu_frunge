package org.nongnu.frunge.converter;

import java.util.Arrays;
import java.util.List;

public class Converters {
	
	protected final static List<String> LANGUAGES = Arrays.asList("de", "en",
			"la");
	
	/**
	 * @return a list of all supported languages
	 */
	public static List<String> supportedLanguages() {
		return LANGUAGES;
	}
	
	/**
	 * @param in
	 *          a language code from the <a
	 *          href="http://en.wikipedia.org/wiki/List_of_ISO_639-1_codes" >List
	 *          of ISO_639-1 codes</a>
	 * @return a matching Converter
	 */
	public static Converter get(String in) {
		String i = in.trim().toLowerCase();
		
		if (i.equals("de")) {
			return new DeConverter();
		}
		if (i.equals("en")) {
			return new EnConverter();
		}
		if (i.equals("la")) {
			return new LaConverter();
		}
		
		return null;
	}
	
}
