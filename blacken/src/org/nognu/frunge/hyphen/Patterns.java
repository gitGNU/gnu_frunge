package org.nognu.frunge.hyphen;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Formattable;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.nognu.frunge.Function;
import org.nognu.frunge.IO;


public class Patterns implements Formattable, Function<String, String> {
		
	protected Map<String, String> pat;
	
	protected List<String> exc;
	
	protected boolean verbose;

	/**
	 * @param l language
	 */
	public Patterns(String l) {
		this(l, false);
	}

	/**
	 * @param l language
	 * @param v verbose
	 */
	public Patterns(String l, boolean v) {
		this.verbose = v;
		this.pat = this.verbose ?
				new TreeMap<String, String>() : // better to read
				new HashMap<String, String>(); // better Performance
		this.exc = new ArrayList<String>();
				
		l = l.equalsIgnoreCase("de") ? "de-1901" : l;
		l = l.equalsIgnoreCase("en") ? "en-gb" : l;
		
		this.load(IO.getReader("hyphen/hyph-"+l+".tex"));
	}
	
	protected void load(BufferedReader r) {
		try {
			boolean pattern = true; // State: true => pattern, false => exception
			
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
						addPattern(s.next());
					} else {		
						addException(s.next());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * p=|.ru5s6ses|
	 * k=|.russes|
	 * v=|   5 6   |
	 */ 
	protected void addPattern(String p) {
		StringBuilder k = new StringBuilder();
		StringBuilder v = new StringBuilder();
		
		for(int i=0; i<p.length(); i++) {
			char c = p.charAt(i);
			if(Character.isDigit(c)) {
				v.append(c);				
			} else { // no letter
				k.append(c);
				v.append(c);
				//v.append(' ');
			}
		}
		
		this.pat.put(k.toString(), v.toString());
	}
	
	protected void addException(String e) {
		this.exc.add(e);
	}

	@Override
	public String apply(String input) {
		Formatter f = new Formatter(verbose ? System.out : new StringBuilder());
		f.format("Word: %s%n", input);
		
		//if exception return it;
		
		int N = input.length();
		int[] weight = new int[N];
		
		String in =  "." + input.toLowerCase() + ".";
		
		for(int l=2; l<=in.length(); l++) {
			f.format("%2d: ", l);
			for(int p=0;p<(in.length()-l+1);p++) {
				String key = in.substring(p, p+l);
				f.format("[%s] ", key);
				String val;
				if((val = this.pat.get(key)) != null) {
					f.format("_%s_%s_ ", key, val);
					for(int i=0; i<l; i++) {
						
					}
				}
			}
			f.format("%n");
		}
		String out = input;
		f.format("-> %s%n", out);
		return out;
	}

	@Override
	public String toString() {
		return String.format("Pattern (%d Words, %d Exceptions)", this.pat.size(), this.exc.size());
	}
	
	@Override
	public void formatTo(Formatter f, int flags, int width, int precision) {
		f.format("Pattern (Words: %s, Exceptions: %s)", this.pat, this.exc);
	}


	public static void main(String... arg) {
		Patterns p = new Patterns("de", true);
		System.out.format("Pattern: %s%n", p);
		
		String k = "Bundestagssitzung";
		System.out.format("Pattern(%s)=%s%n", k, p.apply(k));
	}
	
}
