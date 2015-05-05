package chess.prototype.events.listener;

import chess.core.Piece;
import chess.prototype.composite.CombinePiece;
import chess.prototype.events.ChessEvent;
import chess.prototype.events.PieceMovedEvent;
import chess.prototype.events.PieceSplitEvent;

public class PieceSplitEventListener extends EventListenerBase {

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof PieceSplitEvent)) return;
		
		PieceSplitEvent split = (PieceSplitEvent)event;
		
		int oldPosition = split.getCurrentPosition();
		int newPosition = split.getSplitToPosition();

		PieceMovedEvent capture = new PieceMovedEvent(oldPosition, newPosition);
		if(!(new PieceMovedEventListener().isMoveValid(capture)) &&
				!(new PieceJoinEventListener().isMoveValid(capture)) &&
						!(new PieceCapturedEventListener().isMoveValid(capture)))
			return;

		CombinePiece composite = (CombinePiece) board.getPiece(oldPosition);

		Piece fragment = split.getPieceToSplit();
		composite.remove(fragment);

		if(new PieceJoinEventListener().isMoveValid(capture)) {
			eventMgr.fireEvent(capture);
		} else {
			eventMgr.fireEvent(capture);
			board.getPieces()[newPosition] = fragment;
		}
		board.getPieces()[oldPosition] = composite;
	}
}
