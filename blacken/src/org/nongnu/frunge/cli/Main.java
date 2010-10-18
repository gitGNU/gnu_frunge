package org.nongnu.frunge.cli;

import java.util.Date;
import java.util.Properties;
import java.util.TreeSet;

import org.nongnu.frunge.gui.SwingGui;
import org.nongnu.frunge.io.logging.LoggingBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	
	final static Logger log = LoggerFactory.getLogger(Parser.class);
	
	public static void main(String... arg) {
		LoggingBinding.init();
		
		log.info("Logging ſtarted for blacken at {}. Fraktur is cool!", new Date());
		
		Properties prop = System.getProperties();
		for (String key : new TreeSet<String>(prop.stringPropertyNames())) {
			log.debug("System property {} = {}", key, prop.getProperty(key));
		}
		
		if ((arg.length == 0) && (System.console() == null)) {
			// program was launched from a graphical entourage without console
			new SwingGui();
		} else {
			Parser.main(arg);
		}
	}
	
}
