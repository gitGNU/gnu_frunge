package org.nognu.frunge;


/**
 * @author Dennis Heidsiek
 *
 * @param <T> the type to convert to 
 */
public interface Converter<T> {

	public T apply(String in);
	
}