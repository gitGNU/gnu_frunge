package org.nongnu.frunge.io;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Buffers {
	
	final static Logger log = LoggerFactory.getLogger(Buffers.class);
	
	public static ByteBuffer mapFile(String filename) {
		MappedByteBuffer buffer = null;
		FileChannel fc;
		try {
			fc = (new FileInputStream(filename)).getChannel();
			buffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
		} catch (Exception e) {
			log.error("Can’t map file into memory", e);
		}
		return buffer;
	}
	
	/**
	 * The current implementation is <emph>not</emph> efficient.
	 */
	public static ByteBuffer fromInputStream(InputStream is) {
		int BUFFER_SIZE = 4 * 1024;
		byte[] tmp = new byte[BUFFER_SIZE];
		ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE);
		
		int r;
		while (true) {
			try {
				r = is.read(tmp);
			} catch (IOException e) {
				break;
			}
			if (r == -1) {
				break;
			}
			out.write(tmp, 0, r);
		}
		// tmp = out.toByteArray();
		// ByteBuffer buf = ByteBuffer.wrap(out.toByteArray());
		ByteBuffer buffer = ByteBuffer.wrap(out.toByteArray());
		return buffer;
	}
	
	public static CharBuffer asCharBuffer(ByteBuffer buffer, String charset) {
		return Charset.forName(charset).decode(buffer);
	}
	
	public static CharBuffer mapFile(String filename, String charset) {
		return Buffers.asCharBuffer(Buffers.mapFile(filename), charset);
	}
	
	/**
	 * @return a input steam which share the content with the underlying buffer
	 *         but has a independent position state
	 */
	public static InputStream newInputStream(ByteBuffer buffer) {
		final ByteBuffer b = buffer.slice();
		return new InputStream() {
			@Override
			public synchronized int read() throws IOException {
				return b.hasRemaining() ? b.get() : -1;
			}
			
			@Override
			public synchronized int read(byte[] bytes, int off, int len)
					throws IOException {
				int r = Math.min(len, b.remaining());
				b.get(bytes, off, r);
				return (r == 0) ? -1 : r;
			}
		};
	}
	
}
