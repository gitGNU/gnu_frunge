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
		Cli<CliOptions> cli = CliFactory.createCli(CliOptions.class);
		
		if(arg.length==0) {
			printAbout(System.out);
			System.out.format("%s%n", cli.getHelpMessage());			
			return;
		}		
		
		CliOptions op = null;
		try {
			op = cli.parseArguments(arg);
	    } catch(ArgumentValidationException e) {
	    	printAbout(System.err);
			System.err.format("Error:%n%s%n%s%n", e.getMessage(), cli.getHelpMessage());
			return;
	    }
	    
	    // Now we can interpret the options
	    if(op.isSilent()) {
	    	System.out.format("Shush!%n");
	    }
	    
	    if(op.isVerbose()) {
	    	System.out.format("Your typed %d arguments:%n", arg.length);
			for (int i = 0; i < arg.length; i++) {
				System.out.format("arg[%d]=%s%n", i, arg[i]);
			}
			System.out.format("Evaluated to %s%n", op);
	    }
	    
	    if(op.isTest()) {
	    	new TestRunner(op.isVerbose());
	    }
	}
}