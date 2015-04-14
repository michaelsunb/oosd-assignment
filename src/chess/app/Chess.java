package chess.app;

import javax.swing.SwingUtilities;

import chess.core.Game;
import chess.mvc.views.MainFrame;


public class Chess {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Game game = Game.getInstance();
				game.reset(15);
				
				MainFrame mf = new MainFrame();
				mf.setVisible(true);
			}
			
		});
	}

}
