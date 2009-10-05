package org.nognu.frunge;

//import java.util.List;

import uk.co.flamingpenguin.jewel.cli.CommandLineInterface;
import uk.co.flamingpenguin.jewel.cli.Option;

//import uk.co.flamingpenguin.jewel.cli.Unparsed;

@CommandLineInterface(application = "java -jar blacken.jar")
public interface CliOptions {

	@Option(shortName = "h", longName = "help", description = "print a detailed help message")
	boolean isHelp();
	
	@Option(shortName = "s", longName = "silent", description = "suppresses the whole output")
	boolean isSilent();

	@Option(shortName = "v", longName = "verbose", description = "output detailed messages what you are doing")
	boolean isVerbose();
	
	@Option(shortName = "t", longName = "test", description = "run some testsuits")
	boolean isTest();
	
	@Option(shortName = "p", longName = "pipe", description = "run this program in the middle of a linux pipe")
	boolean isPipe();
	
	@Option(shortName = "f", longName = "format", description = "the input format", defaultValue="plain", pattern = "plain|tex|html")
	String getFormat();
	
}