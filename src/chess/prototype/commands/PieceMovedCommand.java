package chess.prototype.commands;

import chess.core.Piece;
import chess.mvc.models.PieceMovedEvent;
import chess.prototype.observer.ChessEvent;

public class PieceMovedCommand extends MovedDecision {
	private PieceMovedEvent currentEvent;

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof PieceMovedEvent)) return;
		
		this.currentEvent = (PieceMovedEvent) event;
		newPosition = this.currentEvent.getNewPosition();
		oldPosition = this.currentEvent.getPreviousPosition();

		if(!isSelectedPieceValid()) return;
		if(!selectedPiece.canMoveTo(oldPosition, newPosition)) return;

		move();
	}

	@Override
	public boolean move() {
		if(!super.move()) return false;

		// destination is empty so just occupy
		Piece piece = this.getBoard().getPiece(oldPosition);
		this.getBoard().getPieces()[newPosition] = piece;
		this.getBoard().getPieces()[oldPosition] = null;
		piece.getOwner().increaseMove();
		return true;
	}
}
