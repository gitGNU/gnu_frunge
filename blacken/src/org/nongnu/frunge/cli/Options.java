package org.nongnu.frunge.cli;

import java.util.List;

import uk.co.flamingpenguin.jewel.cli.CommandLineInterface;
import uk.co.flamingpenguin.jewel.cli.Option;
import uk.co.flamingpenguin.jewel.cli.Unparsed;

@CommandLineInterface(application = "java -jar blacken.jar")
public interface Options {
	
	@Option(shortName = "h", longName = "help", description = "print a detailed help message")
	boolean help();
	
	@Option(shortName = "s", longName = "silent", description = "suppresses the whole output")
	boolean silent();
	
	@Option(shortName = "v", longName = "verbose", description = "output detailed messages what you are doing")
	boolean verbose();
	
	@Option(shortName = "t", longName = "test", description = "run some testsuits")
	boolean test();
	
	@Option(shortName = "p", longName = "pipe", description = "run this program within a linux pipe")
	boolean pipe();
	
	@Option(shortName = "f", longName = "format", description = "the file format to deal with", defaultValue = "plain", pattern = "plain|tex|html")
	String getFormat();
	
	@Option(shortName = "l", longName = "lang", description = "the language of the input", defaultValue = "de", pattern = "de|en|la")
	String getLang();
	
	@Option(longName = "charsets", description = "Lists all availible charsets.")
	boolean charsets();
	
	@Unparsed(name = "[inputfile outputfile]")
	List<String> getUnparsed();
	
}
