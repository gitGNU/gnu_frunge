package org.nognu.frunge;

//import java.util.List;

import uk.co.flamingpenguin.jewel.cli.CommandLineInterface;
import uk.co.flamingpenguin.jewel.cli.Option;

//import uk.co.flamingpenguin.jewel.cli.Unparsed;

@CommandLineInterface(application = "java -jar blacken.jar")
public interface CliOptions {

	@Option(shortName = "t", longName = "test", description = "run some testsuits")
	boolean isTest();

	@Option(shortName = "s", longName = "silent", description = "suppresses the whole output")
	boolean isSilent();

	@Option(shortName = "v", longName = "verbose", description = "output detailed messages what you are doing")
	boolean isVerbose();

}