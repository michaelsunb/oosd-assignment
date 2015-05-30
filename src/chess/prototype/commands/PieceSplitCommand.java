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
	private Piece fragment;
	private Piece piece;
	
	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof PieceSplitEvent))
			return;
		
		split = (PieceSplitEvent) event;
		
		composite = split.getCurrentPiece();
		oldPosition = Game.getInstance().getSelPosition();
		newPosition = split.getSplitToPosition();
		
		if (!isSelectedPieceNotEmptySqureBarrierOrEnemyPiece())
			return;

		if (!selectedPiece.canMoveTo(oldPosition, newPosition))
			return;

		fragment = split.getSplitPiece();
		fragment.setOwner(composite.getOwner());
		piece = composite.remove(fragment);
		this.getBoard().setPiece(oldPosition, fragment);
		this.moveDecider();
		this.getBoard().getPieces()[oldPosition] = piece;
		ChessEventDispatcher.getInstance().fireEvent(new UpdateUIEvent());
	}

	@Override
	public void fireMoveCommand() {
		// destination is empty so just occupy

		this.getBoard().getPieces()[newPosition] = fragment;
		this.getBoard().getPieces()[oldPosition] = piece;

		piece.getOwner().increaseMove();
		this.getGame().swapPlayer();
	}
}
