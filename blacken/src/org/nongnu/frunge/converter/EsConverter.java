package org.nongnu.frunge.converter;

/**
 * <p>
 * A simple {@link Converter} for the spanish language.
 * </p>
 * <p>
 * For a authentic example of spanish blackletter use, see <a href="http://www.cervantesvirtual.com/servlet/SirveObras/cerv/12371067559018288532624/ima0008.htm"
 * > El ingenioso hidalgo don Quixote de la Mancha</a>,written by
 * <em>Miguel de Cervantes Saavedra</em>.
 * </p>
 * 
 * @author Dennis Heidsiek
 */
public class EsConverter implements Converter {
	
	@Override
	public String apply(String in) {
		return in;
	}
	
}
