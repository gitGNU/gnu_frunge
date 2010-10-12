package org.nongnu.frunge.util;

public class Functions {
	
	/*
	 * Singleton
	 */
	private static final Function<Object, String> STRING_VALUE = new Function<Object, String>() {
		@Override
		public String apply(Object in) {
			return (in == null) ? null : in.toString();
		}
	};
	
	/**
	 * Returns the {@link java.lang.Object#toString()} function, or null for null
	 * keys. Although this method is type-safe, the implementation of this method
	 * will not create a separate {@link Function} object for each call.
	 */
	@SuppressWarnings("unchecked")
	public static final <K> Function<K, String> stringValue() {
		return (Function<K, String>) Functions.STRING_VALUE;
	}
}
