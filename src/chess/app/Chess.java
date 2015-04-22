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

				// setup mvc
				// TODO: we need to learn more to implement MVC properly,
				// current implementation is far from complete
				GameController gameController = new GameController();
				MainFrame view = new MainFrame();
				GameStatusViewPanel statusView = new GameStatusViewPanel();

				view.setVisible(true);

				// Register observers
				eventMgr.addListener("PieceSelectedEvent", new PieceSelectedCommand());
				eventMgr.addListener("PieceMovedEvent", new PieceMovedCommand());
				eventMgr.addListener("GameNewEvent", gameController);
				eventMgr.addListener("PieceCapturedEvent", new PieceCapturedCommand());
				eventMgr.addListener("PieceJoinEvent", new PieceJoinCommand());
				eventMgr.addListener("GameStatusEvent", statusView);
			}

		});
	}

}
