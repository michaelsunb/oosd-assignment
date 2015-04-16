package chess.app;

import javax.swing.SwingUtilities;

import chess.core.Game;
import chess.mvc.controllers.GameController;
import chess.mvc.views.MainFrame;
import chess.piece.commands.ChessPieceAction;
import chess.piece.commands.ChessPieceMove;
import chess.prototype.observer.ChessEventDispatcher;


public class Chess {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				MainFrame view = new MainFrame();

				view.setVisible(true);

				// Register observers
				ChessPieceMove moveListener = new ChessPieceMove();
				ChessEventDispatcher.getInstance().addListener("PieceMovesEvent", moveListener);
				ChessEventDispatcher.getInstance().addListener("PieceMovedEvent", moveListener);
				
				GameController gameController = new GameController();
				ChessEventDispatcher.getInstance().addListener("NewGameEvent", gameController);
				
				ChessPieceAction pieceAction = new ChessPieceAction();
				ChessEventDispatcher.getInstance().addListener("PieceCapturedEvent", pieceAction);
				ChessEventDispatcher.getInstance().addListener("PieceJoinEvent", pieceAction);
			}
			
		});
	}

}
