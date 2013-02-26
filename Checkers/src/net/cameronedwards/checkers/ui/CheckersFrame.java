package net.cameronedwards.checkers.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import net.cameronedwards.checkers.game.GameController;

public class CheckersFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1950523765839225065L;

	
	public CheckersFrame(GameController gc) {
		super("Checkers");
		setResizable(false);
		setLocationByPlatform(true);
		init(gc);
	}
	
	private void init(GameController gc) {
		CheckersPanel checkersPanel = new CheckersPanel(gc);
		add(checkersPanel);
		pack();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				CheckersFrame.this.setVisible(false);
				CheckersFrame.this.dispose();
			}
		});
		
	}
}
