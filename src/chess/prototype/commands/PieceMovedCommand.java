package chess.prototype.commands;

import chess.mvc.models.PieceMovedEvent;
import chess.mvc.models.UpdateUIEvent;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.ChessEventDispatcher;
import chess.prototype.template.MovedDecisionTemplate;

public class PieceMovedCommand extends MovedDecisionTemplate {
	private PieceMovedEvent currentEvent;

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof PieceMovedEvent)) return;
		
		this.currentEvent = (PieceMovedEvent) event;
		newPosition = this.currentEvent.getNewPosition();
		oldPosition = this.currentEvent.getPreviousPosition();
		if (oldPosition == -1) return;
		if(!selectedPiece.canMoveTo(oldPosition, newPosition)) return;

		this.moveDecider();
	}

	public void fireMoveCommand() {
		// destination is empty so just occupy
		this.getBoard().getPieces()[newPosition] = selectedPiece;
		this.getBoard().getPieces()[oldPosition] = null;
		
		selectedPiece.getOwner().increaseMove();
		this.getGame().swapPlayer();
		ChessEventDispatcher.getInstance().fireEvent(new UpdateUIEvent());
	}
}
