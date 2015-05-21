package chess.prototype.commands;

import chess.core.*;
import chess.mvc.models.*;
import chess.prototype.composite.CombinePiece;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.ChessEventDispatcher;
import chess.prototype.template.MovedDecisionTemplate;

public class PieceSplitCommand extends MovedDecisionTemplate {
	private PieceSplitEvent split;
	private CombinePiece composite;

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof PieceSplitEvent))
			return;
		split = (PieceSplitEvent) event;
		composite = split.getCurrentPiece();
		oldPosition = this.getBoard().getPiecePosition(composite);
		newPosition = split.getSplitToPosition();
		if (!selectedPiece.canMoveTo(oldPosition, newPosition))
			return;
		this.getBoard().setPiece(oldPosition, split.getSplitPiece());
		this.moveDecider();
	}

	@Override
	public void fireMoveCommand() {
		// destination is empty so just occupy
		Piece fragment = split.getSplitPiece();
		Piece piece = composite.remove(fragment);

		this.getBoard().getPieces()[newPosition] = fragment;
		this.getBoard().getPieces()[oldPosition] = piece;

		piece.getOwner().increaseMove();
		ChessEventDispatcher.getInstance().fireEvent(new UpdateUIEvent());
	}
}
