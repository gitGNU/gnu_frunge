package org.nognu.frunge;


import java.io.Console;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.ServiceLoader;

import org.nognu.frunge.converter.Converter;

import uk.co.flamingpenguin.jewel.cli.ArgumentValidationException;
import uk.co.flamingpenguin.jewel.cli.Cli;
import uk.co.flamingpenguin.jewel.cli.CliFactory;


/**
 * CliParser for command line invocation.
 * 
 * @author Dennis Heidsiek
 */
public class CliParser {

	protected static String getName() {
		return String.format(
				"Blacken (s to ſ converter)%n" +
				"This programm is free software (GNU General Public License 3 or later)%n"
				);
	}
		
	protected static String getLinks() {
		return String.format(
				"Links:%n" +
				"licence text : http://www.gnu.org/licenses/%n" +
				"source code  : http://git.savannah.gnu.org/cgit/frunge.git/tree/blacken%n" +
		        "project site : http://sv.nongnu.org/p/frunge%n" +
		        "email contact: frunge-external@nongnu.org%n"
		        );
	}
	
	private static String getExamples() {
		return String.format(
				"Example:%n" +
				"    java -jar blacken.jar --verbose --lang en --test%n" +
				"can also be written as%n" +
				"    java -jar blacken.jar -v -l en -t%n" +
				"or even as%n" +
				"    java -jar blacken.jar -vtl en"
				);
	}
	
	protected static void formatTo(PrintStream f, CliOptions op) {
		f.format("CliOptions (help=%b, silent=%b, verbose=%b, test=%b, pipe=%b, format=%s, lang=%s, unparsed=%s)%n",
				op.help(),
				op.silent(),
				op.verbose(),
				op.test(),
				op.pipe(),
				op.getFormat(),
				op.getLang(),
				op.getUnparsed()
            );
		/*
		if(op.getUnparsed() == null) {
			f.format("No unparsed arguments.%n");
		} else {
			for(String s: op.getUnparsed()) {
				f.format("Unparsed: %s%n", s);
			}
		}
		*/
	}
	
	public static void main(String... arg) {
		Cli<CliOptions> cli = CliFactory.createCli(CliOptions.class);
		
		if(arg.length==0) {
			System.out.format("%s%n%s%n", getName(), cli.getHelpMessage());			
			return;
		}
		
		CliOptions op = null;
		try {
			op = cli.parseArguments(arg);
		} catch(ArgumentValidationException e) {
			System.err.format("%s%nError:%n%s%n%n%s%n",
					getName(), e.getMessage(), cli.getHelpMessage());
			return;
		}
		
		// Now we can interpret the options
		
		if(op.silent()) {
			System.out.format("Shush!%n");
		}
		
		if(op.pipe()) {
			new Task(System.in, System.out);
			return;
		}
		
		if(op.help()) {
			System.out.format("%s%n%s%n%s%n%n%s%n",
					getName(), getLinks(), cli.getHelpMessage(), getExamples());			
			return;
	    }

		if(op.verbose()) {
			Console con = System.console();
			System.out.format("Console found: %s%n", (con==null) ? "No :-(" : "Yes!");
			if(con!=null) {
				con.writer().format("Console test: uiaeüöä UIAEÜÖÄ sſß%n");
			}
			
			System.out.format("Your typed %d argument(s):%n", arg.length);
			for (int i = 0; i < arg.length; i++) {
				System.out.format("arg[%d]=%s%n", i, arg[i]);
			}
			
			System.out.format("Evaluated to:%n");
			formatTo(System.out, op);
		}
		
		if(op.test()) {
			new TestRunner(op.getLang(), op.verbose());
		}
	}
}