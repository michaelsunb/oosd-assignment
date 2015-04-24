package chess.prototype.commands;

import chess.mvc.models.PieceMovedEvent;
import chess.prototype.observer.ChessEvent;

public class PieceMovedCommand extends CommandBase {

	@Override
	public void update(ChessEvent event) {
		if(event instanceof PieceMovedEvent) {
			movePiece((PieceMovedEvent) event);
		}
	}

	public void movePiece(PieceMovedEvent event) {
		int newPos = event.getNewPosition();
		int oldPos = event.getPreviousPosition();
		board.getPieces()[newPos] = board.getPiece(oldPos);
		board.getPieces()[oldPos] = null; 
	}
}
