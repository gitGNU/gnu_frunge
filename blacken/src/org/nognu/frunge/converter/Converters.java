package org.nognu.frunge.converter;


public class Converters {
	
	protected static final Converter simpleOne = new Converter() {
		@Override
		public String apply(String in) {
			String out = in.replace('s', 'ſ').substring(0, in.length()-1);
			return out + in.charAt(in.length()-1);
		}
	};

	public static Converter simpleOne() {
		return simpleOne;
	}
	
}