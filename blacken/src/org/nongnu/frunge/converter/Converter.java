package org.nongnu.frunge.converter;

import org.nongnu.frunge.util.Function;

/**
 * Converts a single word from antiqua to fraktur script. For the correct rules
 * for the long ſ, see <a href="">BabelStone:The Rules for Long S</a> or <a
 * href="http://de.wikipedia.org/wiki/Langes_s#Regeln_zur_Verwendung_von_langem_.C5.BF_und_rundem_s"
 * >de.wikipedia: Regeln zur Verwendung von langem ſ und rundem s</a>.
 * 
 * @author Dennis Heidsiek
 */
public interface Converter extends Function<String, String> {
}
