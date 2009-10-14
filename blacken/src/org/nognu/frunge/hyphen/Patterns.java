package org.nognu.frunge.hyphen;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
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

	protected int maxLength;
	
	protected int minLength;
	
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
		
		this.maxLength = 0;
		this.minLength = Integer.MAX_VALUE;
		
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
	 * p=|2z1um.| (6)
	 * k=|zum.| (4)
	 * v=|21000| (5=4+1)
	 */ 
	protected void addPattern(String p) {
		StringBuilder k = new StringBuilder();
		StringBuilder v = new StringBuilder();
		boolean lastDigit = false;
		
		for(int i=0; i<p.length(); i++) {
			char c = p.charAt(i);
			if(Character.isDigit(c)) {
				v.append(c);
				lastDigit = true;
			} else { // is letter
				k.append(c);
				if(lastDigit) {					
					lastDigit = false;
				} else {
					v.append('0');					
				}
				if(i == p.length()-1) {
					v.append('0');	
				}
			}
		}
		
		//*
		if(verbose) {
			System.out.format("Add %10s (%d), %10s (%d) = %10s (%d)%n",
					p, p.length(),
					k, k.length(),
					v, v.length());
		}//*/

		this.maxLength = Math.max(this.maxLength, k.length());
		this.minLength = Math.min(this.minLength, k.length());
		
		this.pat.put(k.toString(), v.toString());
	}
	
	protected void addException(String e) {
		this.exc.add(e);
	}

	@Override
	public String apply(String input) {
		Formatter f = new Formatter(verbose ? System.out : new StringBuilder());
		
		//if exception return it;
		
		int N = input.length();
		StringBuilder weight = new StringBuilder(N+3);
		for(int i=0; i<N+3; i++) {
			weight.append('0');
		}
		
		String in =  "." + input.toLowerCase() + ".";
		
		f.format("Input:%n%s%n", in);
		
		for(int l=2; l<=in.length(); l++) {
			f.format("%2d: ", l);
			for(int p=0;p<(in.length()-l+1);p++) {
				String key = in.substring(p, p+l);
				f.format("[%s] ", key);
				String val;
				if((val = this.pat.get(key)) != null) {
					f.format("%d:__(%s=%s)__ ", p, key, val);
					
					for(int i=0; i<val.length(); i++) {
						char newChar = val.charAt(i);
						int pos = p+i;						
						if(weight.charAt(pos) < newChar) {
							weight.setCharAt(pos, newChar);
						}
					}
				}
			}
			f.format("%n");
		}
		f.format("->%s (weight)%n", weight);
		
		int hyphenCount = 0;
		for(int i=0;i<weight.length();i++) {			
			if(((weight.charAt(i) % 2) == 0) || (i<1+2) || (i>weight.length()-2-2)) {
				weight.setCharAt(i, '0');
			} else {
				hyphenCount++;
			}
		}
		f.format("->%s (weight)%n", weight);
		
		StringBuilder out = new StringBuilder(N+hyphenCount);
		for(int i=0;i<input.length(); i++) {
			out.append(input.charAt(i));
			if(weight.charAt(2+i) != '0') {
				out.append('­'); // soft hypen
			}
		}

		f.format("->  %s%n", out);
		return out.toString();
	}

	@Override
	public String toString() {
		return String.format("Pattern (min=%d, max=%d, %d Words, %d Exceptions)",
				this.minLength, this.maxLength, this.pat.size(), this.exc.size());
	}
	
	@Override
	public void formatTo(Formatter f, int flags, int width, int precision) {
		f.format("%s ", this.toString());		
		f.format(" (Words: %s, Exceptions: %s)%n", this.pat, this.exc);
	}


	public static void main(String... arg) {
		System.out.format("isDigit(.) = %b%n", Character.isDigit(','));
		System.out.format("\'2\' is even = %b%n", (((int) '2') % 2) == 0);
		
		Patterns p = new Patterns("de", true);
		System.out.format("Pattern: %s%n", p.toString());
		
		for(String k : Arrays.asList("Bundestagssitzung", "Wasser")) {
			System.out.format("Pattern(%s)=%s%n", k, p.apply(k));
		}
	}
	
}
