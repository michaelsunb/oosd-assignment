/*
 * Author: Siang Ling
 * Number: s3295217
 */
package chess.prototype.events.listener;

import chess.core.Piece;
import chess.prototype.composite.CombinePiece;
import chess.prototype.events.PieceMovedEvent;
import chess.prototype.template_method.PieceMovedTemplateMethod;

public class PieceJoinEventListener extends PieceMovedTemplateMethod {

	public void update(PieceMovedEvent event) {
		int newPos = event.getNewPosition();
		int oldPos = event.getPreviousPosition();

		Piece pieceSelected = board.getPiece(oldPos);
		Piece pieceTarget = board.getPiece(newPos);

		CombinePiece piece = new CombinePiece();
		piece.add(pieceSelected);
		piece.add(pieceTarget);

		piece.setOwner(pieceSelected.getOwner());

		board.setPiece(newPos, piece);
		board.setPiece(oldPos, null);
	}

	@Override
	public boolean moveDecision(PieceMovedEvent event) {
		if(selectedOwner == targetOwner) {
			return true;
		}
		return false;
	}
}
