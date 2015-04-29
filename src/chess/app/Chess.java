package chess.app;

import javax.swing.SwingUtilities;

import chess.core.ChessEventDispatcher;
import chess.mvc.controllers.*;
import chess.mvc.views.*;
import chess.prototype.events.*;

public class Chess {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ChessEventDispatcher eventMgr = ChessEventDispatcher.getInstance();
				
				GameController gameController = new GameController();
				MainFrame view = new MainFrame(gameController);
				gameController.init(view);

				// Register observers

				eventMgr.addListener("GameNewEvent", gameController.newGame());
				eventMgr.addListener("PieceSelectedEvent", gameController.pieceSelected());
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
