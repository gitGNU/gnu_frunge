package org.nognu.frunge;

import java.io.PrintStream;

/**
 * Parser for command line invocation.
 * 
 * @author Dennis Heidsiek
 */
public class Parser {

	protected static void printAbout(PrintStream f) {
		f.format("Blacken (s to ſ converter)%n");
		f.format("This programm is free software (GNU General Public License 3 or later)%n%n");

		f.format("Links:%n");
		f.format("licence text : http://www.gnu.org/licenses/%n");
		f.format("source code  : http://git.savannah.gnu.org/cgit/frunge.git/tree/blacken%n");
		f.format("project site : http://sv.nongnu.org/p/frunge%n");
		f.format("email contact: frunge-external@nongnu.org%n%n");
	}

	public static void main(String... arg) {
		printAbout(System.out);

		System.out.format("Your typed the arguments:%n");
		for (int i = 0; i < arg.length; i++) {
			System.out.format("arg[%d]=%s%n", i, arg[i]);
		}
		System.out.format("%n");

		if ((arg.length==0) || (arg[0].equals("-test"))) {
			new TestRunner();
		}
	}
}