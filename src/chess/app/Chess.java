package chess.app;

import chess.core.Game;
import chess.mvc.controllers.GameController;
import chess.mvc.views.MainFrame;
import chess.prototype.observer.ChessEventDispatcher;


public class Chess {

	public static void main(String[] args) {
		Game model = Game.getInstance(); // model
		MainFrame view = new MainFrame(model); // view
		GameController gameController = new GameController(); // controller
		
		ChessEventDispatcher.getInstance().addListener("PieceMovesEvent", gameController);
		
		view.setVisible(true);
	}

}
