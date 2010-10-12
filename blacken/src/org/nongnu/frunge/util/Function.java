package org.nongnu.frunge.util;

/**
 * A function maps keys to values.
 * 
 * @param <K>
 *          type of the input values
 * @param <V>
 *          type of the resulting values
 * @see Functions
 * @author Dennis Heidsiek
 */
public interface Function<K, V> {
	
	public V apply(K in);
	
}
