package com.dm.gui.samples;

import javax.swing.SwingUtilities;

public class MyApplication
{

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() ->
		{
			MyTopFrame frame = new MyTopFrame();
			frame.setVisible(true);
		});
	}

}
