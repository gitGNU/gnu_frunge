package org.nongnu.frunge.cli;

import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.TreeSet;

import org.nongnu.frunge.gui.SwingGui;
import org.nongnu.frunge.io.Resources;
import org.nongnu.frunge.io.logging.LoggingBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	
	final static Logger log = LoggerFactory.getLogger(Parser.class);
	
	public static void main(String... arg) {
		LoggingBinding.init();
		
		log.info("Logging ſtarted for blacken at {}. Fraktur is cool!", new Date());
		
		Properties prop = new Properties();
		try {
			prop.load(Resources.getStream("META-INF/MANIFEST.MF", ".*blacken.jar.*"));
			for (String key : new TreeSet<String>(prop.stringPropertyNames())) {
				log.debug("MANIFEST.MF {} = {}", key, prop.getProperty(key));
			}
		} catch (Exception e) {
		}
		
		try {
			prop.load(Resources.getStream("META-INF/MANIFEST.MF"));
			for (String key : new TreeSet<String>(prop.stringPropertyNames())) {
				log.debug("MANIFEST.MF {} = {}", key, prop.getProperty(key));
			}
		} catch (Exception e) {
		}
		
		prop = System.getProperties();
		for (String key : new TreeSet<String>(prop.stringPropertyNames())) {
			log.debug("System property {} = {}", key, prop.getProperty(key));
		}
		
		Map<String, String> env = System.getenv();
		for (String key : new TreeSet<String>(env.keySet())) {
			log.debug("System env {} = {}", key, env.get(key));
		}
		
		if ((arg.length == 0) && (System.console() == null)) {
			// program was launched from a graphical entourage without console
			new SwingGui();
		} else {
			Parser.main(arg);
		}
	}
	
}
