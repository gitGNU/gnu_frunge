package org.nongnu.frunge.core;

import java.io.Closeable;
import java.io.IOException;

import org.nongnu.frunge.converter.Converter;
import org.nongnu.frunge.format.Format;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dh
 */
public class Task implements Runnable {
	
	final static Logger log = LoggerFactory.getLogger(Task.class);
	
	protected Readable in;
	protected Appendable out;
	protected Converter converter;
	protected Format format;
	
	public static class Builder {
		protected Readable li;
		protected Appendable lo;
		protected Converter lc;
		protected Format lf;
		
		public Builder() {
		}
		
		public Builder in(Readable i) {
			this.li = i;
			return this;
		}
		
		public Builder out(Appendable o) {
			this.lo = o;
			return this;
		}
		
		public Builder converter(Converter c) {
			this.lc = c;
			return this;
		}
		
		public Builder format(Format f) {
			this.lf = f;
			return this;
		}
		
		public Task create() {
			Task task = new Task();
			task.in = this.li;
			task.out = this.lo;
			task.converter = this.lc;
			task.format = this.lf;
			return task;
		}
	}
	
	protected Task() {
		super();
	}
	
	@Override
	public void run() {
		try {
			this.format.process(this.in, this.out, this.converter);
		} catch (IOException e) {
			log.error("There’s an serious io problem", e);
		} finally {
			try {
				((Closeable) this.in).close();
			} catch (IOException e) {
				log.warn("Cant't close in", e);
			}
			try {
				((Closeable) this.out).close();
			} catch (IOException e) {
				log.warn("Cant't close out", e);
			}
		}
	}
	
}
