package org.nognu.frunge;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/*
 * Just a very first draft!
 */
public class Task {

	public Task(InputStream in, OutputStream out) {		
		int b;
		try {
			while((b = in.read()) != -1) {
				if(b=='s') {
					out.write(0xC5);
					out.write(0xBF);
				} else {
					out.write(b);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
