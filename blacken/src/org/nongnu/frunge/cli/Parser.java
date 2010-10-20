package org.nongnu.frunge.cli;

import java.io.Console;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

import org.nongnu.frunge.converter.Converters;
import org.nongnu.frunge.core.Task;
import org.nongnu.frunge.core.TestRunner;
import org.nongnu.frunge.format.PlainTextFormat;
import org.nongnu.frunge.io.CharsetDetector;
import org.nongnu.frunge.io.Resources;
import org.nongnu.frunge.io.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.flamingpenguin.jewel.cli.ArgumentValidationException;
import uk.co.flamingpenguin.jewel.cli.Cli;
import uk.co.flamingpenguin.jewel.cli.CliFactory;

/**
 * Parser for command line invocation.
 * 
 * @author Dennis Heidsiek
 */
public class Parser {
	
	final static Logger log = LoggerFactory.getLogger(Parser.class);
	
	protected static String getName() {
		return String.format("Blacken (s to ſ converter)%n"
				+ "This programm is free software (GNU General Public License 3 or later)%n");
	}
	
	protected static String getLinks() {
		return String.format("Links:%n"
				+ "licence text : http://www.gnu.org/licenses/%n"
				+ "source code  : http://git.savannah.gnu.org/cgit/frunge.git/tree/blacken%n"
				+ "project site : http://sv.nongnu.org/p/frunge%n"
				+ "email contact: frunge-external@nongnu.org%n");
	}
	
	private static String getExamples() {
		return String.format("Example:%n"
				+ "    java -jar blacken.jar --verbose --lang en --test%n"
				+ "can also be written as%n"
				+ "    java -jar blacken.jar -v -l en -t%n" + "or even as%n"
				+ "    java -jar blacken.jar -vtl en");
	}
	
	protected static void formatTo(PrintStream f, Options op) {
		f.format(
				"Options (help=%b, silent=%b, verbose=%b, test=%b, pipe=%b, format=%s, lang=%s, unparsed=%s)%n",
				op.help(), op.silent(), op.verbose(), op.test(), op.pipe(),
				op.getFormat(), op.getLang(), op.getUnparsed());
		/*
		 * if(op.getUnparsed() == null) { f.format("No unparsed arguments.%n"); }
		 * else { for(String s: op.getUnparsed()) { f.format("Unparsed: %s%n", s); }
		 * }
		 */
	}
	
	public static void main(String... arg) {
		long timing = System.nanoTime();
		
		Cli<Options> cli = CliFactory.createCli(Options.class);
		if (arg.length == 0) {
			System.out.format("%s%n%s%n", Parser.getName(), cli.getHelpMessage());
			return;
		}
		
		Options op = null;
		try {
			op = cli.parseArguments(arg);
		} catch (ArgumentValidationException e) {
			log.error("Invalid command line argument", e);
			System.err.format("%n%s%n%n%s%n", Parser.getName(), e.getMessage(),
					cli.getHelpMessage());
			return;
		}
		
		// Now we can interpret the options
		
		if (op.silent()) {
			log.info("Shush!");
		}
		
		if (op.help()) {
			System.out.format("%s%n%s%n%s%n%n%s%n", Parser.getName(),
					Parser.getLinks(), cli.getHelpMessage(), Parser.getExamples());
			return;
		}
		
		if (op.verbose()) {
			Console con = System.console();
			log.info("Console found: {}", (con == null) ? "No :-(" : "Yes!");
			if (con != null) {
				con.writer().format("Console test: üöä ÜÖÄ sſßẞ%n");
			}
			log.info("System class path: {}", System.getProperty("java.class.path"));
			
			log.info("Your typed {} argument(s):", arg.length);
			for (int i = 0; i < arg.length; i++) {
				log.info("arg[{}]={}", i, arg[i]);
			}
			
			log.info("Evaluated to:");
			Parser.formatTo(System.out, op);
			
			log.info("Input Charset: {}", op.inputCharset());
		}
		
		if (op.test()) {
			new TestRunner(op.getLang(), op.verbose());
		}
		
		if (op.charsets()) {
			SortedMap<String, Charset> charsets = Charset.availableCharsets();
			ArrayList<String> names = new ArrayList<String>(6 * charsets.size());
			for (String s : charsets.keySet()) {
				names.add(s);
				for (String a : charsets.get(s).aliases()) {
					names.add(String.format("%s (Alias of %s)", a, s));
				}
			}
			Collections.sort(names);
			for (String s : names) {
				System.out.format("%s%n", s);
			}
			return;
		}
		
		Task.Builder job = new Task.Builder();
		job.format(new PlainTextFormat()).converter(Converters.get(op.getLang()));
		
		if (op.pipe()) {
			job.in(CharsetDetector.asReader(System.in));
			job.out(Streams.asWriter(System.out, "UTF-8"));
		}
		
		if (op.getUnparsed() != null) {
			if (op.getUnparsed().size() == 2) {
				job.in(CharsetDetector.asReader(Resources.getStream(op.getUnparsed().get(
						0))));
				job.out(Streams.getWriter(op.getUnparsed().get(1)));
			}
		}
		
		// Let’s start the real action!
		job.create().run();
		
		if (op.verbose()) {
			timing = System.nanoTime() - timing;
			log.info(
					"Time elapsed: {} ſeconds (and {} milliſeconds). Frunge ſays Goodby!",
					TimeUnit.NANOSECONDS.toSeconds(timing),
					TimeUnit.NANOSECONDS.toMillis(timing) % 1000);
		}
		
	}
}
