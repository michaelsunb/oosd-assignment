package chess.prototype.commands;

import chess.core.*;
import chess.mvc.models.*;
import chess.prototype.composite.CombinePiece;
import chess.prototype.observer.ChessEvent;

public class PieceSplitCommand extends CommandBase {

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof PieceSplitEvent)) return;
		
		PieceSplitEvent split = (PieceSplitEvent)event;
		
		CombinePiece composite = split.getCurrentPiece();
		
		Piece fragment = split.getSplitPiece();
		Piece remain = composite.remove(fragment);
		
		int oldPos = this.getBoard().getPiecePosition(composite);
		int newPos = split.getSplitToPosition();
		
		/*
		 * This is interesting
		 * 1. If the detination is empty: move
		 * 2. If destination is own piece: join
		 * 3. If destination is enemy: capture
		 */
		if (this.getBoard().isSqureEmpty(newPos)) {
			
			this.getBoard().setPiece(newPos, fragment);
			this.getBoard().setPiece(oldPos, remain);
			
			fragment.getOwner().increaseMove();
			return;
		}
		
		// this.getBoard().setPiece(split.getSplitToPosition(), );
	}

}
