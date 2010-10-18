package org.nongnu.frunge.gui;

// import java.awt.*;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dennis Heidsiek
 */
public class SwingGui {
	
	final static Logger log = LoggerFactory.getLogger(SwingGui.class);
	
	public SwingGui() {
		log.info("Launching Swing Gui");
		
		final JTextArea text = new JTextArea();
		
		new FileDrop(text, new FileDrop.Listener() {
			public void filesDropped(File[] files) {
				for (File f : files) {
					try {
						String s = f.getCanonicalPath();
						log.info("Dropped file {} into window", s);
						text.append(s + "\n");
					} catch (java.io.IOException e) {
						log.error("Can't find file", e);
					}
				}
			}
		});
		
		JFrame frame = new JFrame("Blacken – Drag and Drop GUI");
		frame.add(text);
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String... arg) {
		new SwingGui();
	}
}
