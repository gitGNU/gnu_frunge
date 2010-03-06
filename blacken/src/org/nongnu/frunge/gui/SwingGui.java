package org.nongnu.frunge.gui;

import javax.swing.JFrame;
import javax.swing.JTextArea;
/**
 * @author Dennis Heidsiek
 */
public class SwingGui {

	public SwingGui() {
		
		final JTextArea text = new JTextArea();
		new FileDrop(System.out, text, new FileDrop.Listener() {
			public void filesDropped(java.io.File[] files) {
				for (int i = 0; i < files.length; i++) {
					try {
						text.append(files[i].getCanonicalPath() + "\n");
					} catch (java.io.IOException e) {
					}
				}
			}
		});

		JFrame frame = new JFrame("Blacken – Drag and Drop GUI");
		frame.add(text);
		frame.setBounds(100, 100, 300, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String... arg) {
		new SwingGui();
	}

}
