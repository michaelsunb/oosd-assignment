/*
 * Author: Siang Ling
 * Number: s3295217
 */
package chess.prototype.commands;

import chess.core.Piece;
import chess.mvc.models.PieceJoinEvent;
import chess.prototype.composite.CombinePiece;
import chess.prototype.observer.ChessEvent;

public class PieceJoinCommand extends CommandBase {

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof PieceJoinEvent)) return;
		
		update((PieceJoinEvent) event);
	}

	public void update(PieceJoinEvent event) {
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
}
