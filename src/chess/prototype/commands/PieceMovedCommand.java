package chess.prototype.commands;

import chess.core.Game;
import chess.core.Piece;
import chess.mvc.models.PieceMovedEvent;
import chess.mvc.models.UpdateUIEvent;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.ChessEventDispatcher;

public class PieceMovedCommand extends MovedDecision {
	private PieceMovedEvent currentEvent;

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof PieceMovedEvent)) return;
		
		this.currentEvent = (PieceMovedEvent) event;
		newPosition = this.currentEvent.getNewPosition();
		oldPosition = this.currentEvent.getPreviousPosition();
		
		if (oldPosition == -1 || 
				!isSelectedPieceNotEmptySqureBarrierOrEnemyPiece()) {
			return;
		}
		
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
		this.getGame().swapPlayer();
		ChessEventDispatcher.getInstance().fireEvent(new UpdateUIEvent());
		return true;
	}
}
