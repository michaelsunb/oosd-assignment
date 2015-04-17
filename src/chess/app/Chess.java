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
				ChessPieceMove moveListener = new ChessPieceMove();
				eventMgr.addListener("PieceSelectedEvent", moveListener);
				eventMgr.addListener("PieceMovedEvent", moveListener);
				
				eventMgr.addListener("GameNewEvent", gameController);
				
				ChessPieceAction pieceAction = new ChessPieceAction();
				eventMgr.addListener("PieceCapturedEvent", pieceAction);
				eventMgr.addListener("PieceJoinEvent", pieceAction);
				
				eventMgr.addListener("GameStatusEvent", statusView);
			}
			
		});
	}

}
