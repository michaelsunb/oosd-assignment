package chess.app;

import javax.swing.SwingUtilities;

import chess.mvc.controllers.*;
import chess.mvc.views.*;
import chess.prototype.commands.*;
import chess.prototype.observer.*;

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
				eventMgr.addListener("PieceMovedEvent", new PieceMovedCommand());
				eventMgr.addListener("PieceCapturedEvent", new PieceCapturedCommand());
				eventMgr.addListener("PieceJoinEvent", new PieceJoinCommand());
				eventMgr.addListener("GameStatusEvent", statusView);
				*/
			}

		});
	}

}
