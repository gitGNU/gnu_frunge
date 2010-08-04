package org.nongnu.frunge.gui;

//import java.awt.*;
import java.io.File;

import javax.swing.*;

/**
 * @author Dennis Heidsiek
 */
public class SwingGui {

	public SwingGui() {
		
		final JTextArea text = new JTextArea();
		new FileDrop(text, new FileDrop.Listener() {
			public void filesDropped(File[] files) {
				for (File f : files) {
					try {
						text.append(f.getCanonicalPath() + "\n");
					} catch (java.io.IOException e) {
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
