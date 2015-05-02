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
		
		joinPiece((PieceJoinEvent) event);
	}

	public void joinPiece(PieceJoinEvent event) {
		int newPos = event.getNewPosition();
		int oldPos = event.getPreviousPosition();

		Piece pieceSelected = this.getBoard().getPiece(oldPos);
		Piece pieceTarget = this.getBoard().getPiece(newPos);

		CombinePiece piece = new CombinePiece();
		piece.add(pieceSelected);
		piece.add(pieceTarget);

		piece.setOwner(pieceSelected.getOwner());

		this.getBoard().setPiece(newPos, piece);
		this.getBoard().setPiece(oldPos, null);
	}
}
