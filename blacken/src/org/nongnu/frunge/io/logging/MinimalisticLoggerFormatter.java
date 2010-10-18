package org.nongnu.frunge.io.logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * This very minimalistic and basic formatter logs every record on a single
 * line.
 */
public class MinimalisticLoggerFormatter extends Formatter {
	
	@Override
	public String format(LogRecord record) {
		// ToDo: Rewrite onto StringBuilder for better performance
		return String.format("%d %6s: %s%n", record.getMillis(), record.getLevel(),
				record.getMessage());
	}
	
}
