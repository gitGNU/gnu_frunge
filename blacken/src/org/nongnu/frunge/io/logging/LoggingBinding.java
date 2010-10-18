package org.nongnu.frunge.io.logging;

import java.util.logging.LogManager;

import org.nongnu.frunge.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingBinding {
	
	final static Logger log = LoggerFactory.getLogger(LoggingBinding.class);
	
	public static void init() {
		LogManager lm = LogManager.getLogManager();
		try {
			lm.readConfiguration(Resources.getStream("blacken.logging.properties"));
		} catch (Exception e) {
			log.error("Cant't open logging file", e);
		}
	}
}
