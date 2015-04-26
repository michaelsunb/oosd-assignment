package chess.prototype.commands;

import chess.core.Board;
import chess.mvc.models.PieceMovedEvent;
import chess.prototype.observer.ChessEvent;

public class PieceMovedCommand extends CommandBase implements CommandMoveDecision {

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof PieceMovedEvent)) return;
		if (!commandMoveDecision((PieceMovedEvent) event)) return;
		
		movePiece((PieceMovedEvent) event);
	}

	public void movePiece(PieceMovedEvent event) {
		int newPos = event.getNewPosition();
		int oldPos = event.getPreviousPosition();
		board.getPieces()[newPos] = board.getPiece(oldPos);
		board.getPieces()[oldPos] = null; 
	}

	@Override
	public boolean commandMoveDecision(PieceMovedEvent event) {
		if(!isSelectedPieceValid(event)) return false;

		for(int i : allMovableSquares) {
			if (i == newPosition) {
				if(((Board) board).isSqureEmpty(newPosition)) {
					return true;
				}
			}
		}

		return false;
	}
}
