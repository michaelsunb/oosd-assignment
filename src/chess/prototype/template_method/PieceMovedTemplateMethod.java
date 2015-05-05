package chess.prototype.template_method;

import chess.core.Piece;
import chess.core.Player;
import chess.prototype.events.PieceMovedEvent;
import chess.prototype.events.ChessEvent;
import chess.prototype.events.listener.EventListenerBase;

public abstract class PieceMovedTemplateMethod extends EventListenerBase {

	protected int newPosition;
	protected int oldPosition;
	protected Piece selectedPiece;
	protected Player selectedOwner;
	protected Player targetOwner;

	@Override
	public void update(ChessEvent event) {
		if (!isMoveValid(event)) return;
		update((PieceMovedEvent) event); // call abstract update
	}
	
	public boolean isMoveValid(ChessEvent event) {
		if (!(event instanceof PieceMovedEvent)) return false;
		if (!isSelectedPieceValid((PieceMovedEvent) event)) return false;
		if (!selectedPiece.canMoveTo(oldPosition, newPosition)) return false;
		if (!moveDecision((PieceMovedEvent) event)) return false; // call abstract moveDecision
		
		return true;
	}

	private boolean isSelectedPieceValid(PieceMovedEvent event) {
		newPosition = event.getNewPosition();
		oldPosition = event.getPreviousPosition();
		selectedPiece = board.getPiece(oldPosition);

		if (selectedPiece == null || // selected piece is an empty square
				(selectedPiece != null && // selected piece has no owner
						selectedPiece.getOwner() == null))
			return false;
		
		selectedOwner = selectedPiece.getOwner();

		targetOwner = (board.getPiece(newPosition) != null) ? board
				.getPiece(newPosition).getOwner() : null;
		
		return true;
	}

	public abstract void update(PieceMovedEvent event);
	public abstract boolean moveDecision(PieceMovedEvent event);
}
