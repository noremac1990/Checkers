package net.cameronedwards.checkers;

import javax.swing.SwingUtilities;

import net.cameronedwards.checkers.game.GameController;
import net.cameronedwards.checkers.ui.CheckersFrame;

public class Checkers {
	public static void main(String... args) {
		
		final GameController gc = new GameController();
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new CheckersFrame(gc).setVisible(true);
			}
			
		});
		
		
	}
}
