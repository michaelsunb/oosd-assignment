package chess.prototype.commands;

import chess.core.*;
import chess.mvc.models.*;
import chess.prototype.composite.CombinePiece;
import chess.prototype.observer.ChessEvent;

public class PieceSplitCommand extends MovedDecision {
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

		if (!isSelectedPieceNotEmptySqureBarrierOrEnemyPiece() 
				|| !selectedPiece.canMoveTo(oldPosition, newPosition))
			return;

		this.getBoard().setPiece(oldPosition, split.getSplitPiece());
		move();
	}

	@Override
	public boolean move() {
		if (!super.move())
			return false;

		// destination is empty so just occupy
		Piece fragment = split.getSplitPiece();
		Piece piece = composite.remove(fragment);

		this.getBoard().getPieces()[newPosition] = fragment;
		this.getBoard().getPieces()[oldPosition] = piece;

		piece.getOwner().increaseMove();
		return true;
	}
}
