package org.nongnu.frunge.gui;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class SwingGui {
  public SwingGui() {
    ;
  }

    /** Runs a simple sample program that shows how to use {@link FileDrop}
     * @author Robert Harder, rob@iharder.net
    */
    public static void main( String[] args )
    {
        JFrame frame = new JFrame( "FileDrop" );
        //javax.swing.border.TitledBorder dragBorder = new javax.swing.border.TitledBorder( "Drop 'em" );
        final JTextArea text = new JTextArea();
        frame.getContentPane().add( 
            new javax.swing.JScrollPane( text ), 
            java.awt.BorderLayout.CENTER );
        
        new FileDrop( System.out, text, /*dragBorder,*/ new FileDrop.Listener()
        {   public void filesDropped( java.io.File[] files )
            {   for( int i = 0; i < files.length; i++ )
                {   try
                    {   text.append( files[i].getCanonicalPath() + "\n" );
                    }
                    catch( java.io.IOException e ) {}
                }
            }
        });

        frame.setBounds( 100, 100, 300, 400 );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
