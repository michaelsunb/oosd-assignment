package chess.app;

import javax.swing.SwingUtilities;

import chess.core.Game;
import chess.mvc.controllers.GameController;
import chess.mvc.views.MainFrame;
import chess.prototype.observer.ChessEventDispatcher;


public class Chess {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Game model = Game.getInstance();
				MainFrame view = new MainFrame(model);
				GameController gameController = new GameController();

				view.setVisible(true);
				
				ChessEventDispatcher.getInstance().addListener("PieceMovesEvent", gameController);
				ChessEventDispatcher.getInstance().addListener("PieceMovedEvent", gameController);
			}
			
		});
	}

}
