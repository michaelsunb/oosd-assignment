package chess.app;

import javax.swing.SwingUtilities;

import chess.mvc.controllers.GameController;
import chess.mvc.views.MainFrame;
import chess.prototype.observer.ChessEventDispatcher;

public class Chess {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				ChessEventDispatcher eventMgr = ChessEventDispatcher.getInstance();
				
				
				GameController gameController = new GameController();
				MainFrame view = new MainFrame(gameController);

				// Register observers
				eventMgr.addListener("GameNewEvent", gameController.newGame());
				eventMgr.addListener("PieceSelectedEvent", gameController.pieceSelected());
				eventMgr.addListener("PieceMovedEvent", gameController.pieceMoved());
				eventMgr.addListener("PieceCapturedEvent", gameController.pieceCapture());
				eventMgr.addListener("PieceJoinEvent", gameController.pieceJoin());

				eventMgr.addListener("UpdateUIEvent", view.getChessBoardPane());
				eventMgr.addListener("UpdateUIEvent", view.getStatusPane());
				/*
				eventMgr.addListener("PieceCommandDecisionEvent", new CommandDecisionMake());
				eventMgr.addListener("GameNewEvent", gameController);
				eventMgr.addListener("PieceSelectedEvent", new PieceSelectedCommand());
				eventMgr.addListener("PieceMovedEvent", new PieceMovedCommand());
				eventMgr.addListener("PieceMovedEvent", new PieceCapturedCommand());
				eventMgr.addListener("PieceMovedEvent", new PieceJoinCommand());
				eventMgr.addListener("GameStatusEvent", statusView);
				*/
			}

		});
	}

}
