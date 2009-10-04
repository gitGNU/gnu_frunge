package org.nognu.frunge.hyphen;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Formattable;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

import org.nognu.frunge.IO;


public class Patterns implements Formattable {
	
	protected List<String> pat;
	
	protected List<String> exc;
	
	
	/**
	 * @param l language
	 */
	public Patterns(String l) {
		this.pat = new ArrayList<String>();
		this.exc = new ArrayList<String>();
		
		l = l.equalsIgnoreCase("de") ? "de-1901" : l;
		l = l.equalsIgnoreCase("en") ? "en-gb" : l;
		
		this.load(IO.getReader("hyphen/hyph-"+l+".tex"));
	}
	
	protected void load(BufferedReader r) {
		try {
			boolean pattern = true; // true => pattern, false => exception
						
			String line;
			while ((line = r.readLine()) != null) {
				int pos = line.indexOf("%");
				if(pos!=-1) {
					line = line.substring(0, pos);
				}
				line = line.replace("{","");
				line = line.replace("}","");
				line = line.trim();
				
				if(line.equals("")) {
					continue;
				}
				if(line.startsWith("\\")) {
					if(line.equals("\\patterns")) pattern=true;
					if(line.equals("\\hyphenation")) pattern=false;
					continue;
				}
				
				Scanner s = new Scanner(line);
				while(s.hasNext()) {
					if(pattern) {
						this.pat.add(s.next());
					} else {
						this.exc.add(s.next());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void addPattern(String p) {
		;
	}
	
	protected void addException(String p) {
		;
	}

	@Override
	public void formatTo(Formatter f, int flags, int width, int precision) {
		f.format("Pattern (Words: %s, Exceptions: %s)", this.pat, this.exc);
	}
	
}
