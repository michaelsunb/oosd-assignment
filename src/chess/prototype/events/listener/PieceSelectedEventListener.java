package chess.prototype.events.listener;

import chess.core.Piece;
import chess.mvc.views.ChessboardViewPanel;
import chess.prototype.events.ChessEvent;
import chess.prototype.events.PieceSelectedEvent;

public class PieceSelectedEventListener extends EventListenerBase {

	@Override
	public void update(ChessEvent event) {
		if (!((event instanceof PieceSelectedEvent))) return;
		
		PieceSelectedEvent selEvent = (PieceSelectedEvent)event;
		
		Piece piece = this.board.getPiece(selEvent.getPosition());
		if (piece == null) return;
		
		ChessboardViewPanel chessPane = selEvent.getChessboardViewPanel();
		int currPos = selEvent.getPosition();
		
		int movablePos[] = piece.getMovablePositions(currPos);
		
		chessPane.clearPath();
		
		for (int pos : movablePos) {
			chessPane.markPath(pos);
		}
	}

}
