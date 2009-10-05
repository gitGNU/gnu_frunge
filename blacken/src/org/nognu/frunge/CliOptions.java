package org.nognu.frunge;

//import java.util.List;

import uk.co.flamingpenguin.jewel.cli.CommandLineInterface;
import uk.co.flamingpenguin.jewel.cli.Option;

//import uk.co.flamingpenguin.jewel.cli.Unparsed;

@CommandLineInterface(application = "java -jar bracken.jar")
public interface CliOptions {

	@Option(shortName = "t", longName = "test", description = "run some testsuits")
	boolean isTest();

	@Option(shortName = "v", longName = "verbose", description = "explain what is being done")
	boolean isVerbose();

}