package org.nongnu.frunge;

import java.util.Formattable;
import java.util.Formatter;


public class Metric implements Formattable {

	/* Counter */

	protected int sTotal;
	
	protected int ſTotal;

	protected int sCorrect;
	
	protected int ſCorrect;	
	
	
	public Metric() {
		super();
	}
	
	public void addCase(String expected, String actual) {		
		for(int pos=0; pos<expected.length(); pos++) {
			char e = expected.charAt(pos);
			if(e=='s') {
				char a = actual.charAt(pos);
				this.sTotal++;
				if(e==a) this.sCorrect++;
			}
			if(e=='ſ') {
				char a = actual.charAt(pos);
				this.ſTotal++;
				if(e==a) this.ſCorrect++;
			}
		}
	}
	
	@Override
	public void formatTo(Formatter f, int flags, int width, int precision) {
		f.format("Result Metric (Correct: %s, s: %s, ſ: %s)",
				percent(sCorrect+ſCorrect, sTotal+ſTotal),
				percent(sCorrect, sTotal),
				percent(ſCorrect, ſTotal)
				);
	}
	
	protected String percent(int a, int b) {
		return String.format("%.2f%% = %3d/%3d", (double) (100*a) / b, a, b);
	}
}
