package org.nognu.frunge;

import java.io.PrintStream;


import uk.co.flamingpenguin.jewel.cli.ArgumentValidationException;
import uk.co.flamingpenguin.jewel.cli.Cli;
import uk.co.flamingpenguin.jewel.cli.CliFactory;

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

		System.out.format("Your typed %d arguments:%n", arg.length);
		for (int i = 0; i < arg.length; i++) {
			System.out.format("arg[%d]=%s%n", i, arg[i]);
		}
		
		System.out.format("%n");
		try {
			Cli<CliOptions> cli = CliFactory.createCli(CliOptions.class);
	        System.out.format("%s%n%n", cli.getHelpMessage());
	        
	        CliOptions op = cli.parseArguments(arg);
	        System.out.format("(%s)%n", op);
	        System.out.format("Test: %b%n%n", op.isTest());
	    } catch(ArgumentValidationException ave) {
	        ave.printStackTrace(System.err);
	    }
		
		
		//new TestRunner();
	}
}