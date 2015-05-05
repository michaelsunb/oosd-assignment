package chess.prototype.events.listener;

import chess.core.Board;
import chess.core.Piece;
import chess.core.Player;
import chess.prototype.events.PieceMovedEvent;
import chess.prototype.template_method.PieceMovedTemplateMethod;

public class PieceCapturedEventListener extends PieceMovedTemplateMethod {

	public void update(PieceMovedEvent event) {
		int newPos = event.getNewPosition();
		int oldPos = event.getPreviousPosition();

		Piece pieceSelected = board.getPiece(oldPos);
		Piece pieceEnemy = board.getPiece(newPos);

		//if(pieceEnemy == null) return; // TODO

		Player playerSelected = pieceSelected.getOwner();

		playerSelected.addScore(pieceEnemy.getScore());
		board.setPiece(newPos, pieceSelected);
	}

	@Override
	public boolean moveDecision(PieceMovedEvent event) {
		if(!((Board) board).isSqureEmpty(newPosition) &&
				(targetOwner == null || selectedOwner != targetOwner)) {
			return true;
		}
		return false;
	}
}
