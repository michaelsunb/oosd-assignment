package chess.prototype.events.listener;

import chess.core.Board;
import chess.prototype.events.PieceMovedEvent;
import chess.prototype.template_method.PieceMovedTemplateMethod;

public class PieceMovedEventListener extends PieceMovedTemplateMethod {

	@Override
	public void update(PieceMovedEvent event) {
		// destination is empty so just occupy
		board.getPieces()[newPosition] = board.getPiece(oldPosition);
		board.getPieces()[oldPosition] = null;
	}

	@Override
	public boolean moveDecision(PieceMovedEvent event) {
		if(((Board) board).isSqureEmpty(newPosition)) {
			return true;
		}
		return false;
	}
}
