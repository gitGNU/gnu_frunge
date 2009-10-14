package org.nognu.frunge.converter;


public class LaConverter implements Converter {

	@Override
	public String apply(String in) {
		String out = in.replace('s', 'ſ').substring(0, in.length()-1);
		return out + in.charAt(in.length()-1);
	}
	
}