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
		newPosition = event.getNewPosition();
		oldPosition = event.getPreviousPosition();

		Piece pieceSelected = this.getBoard().getPiece(oldPosition);
		Piece pieceTarget = this.getBoard().getPiece(newPosition);

		CombinePiece piece = new CombinePiece();
		piece.setOwner(pieceSelected.getOwner());
		if(!piece.add(pieceSelected)) return;
		if(!piece.add(pieceTarget)) return;
		
		this.getBoard().setPiece(newPosition, piece);
		this.getBoard().setPiece(oldPosition, null);
		
		piece.getOwner().increaseMove();
		this.getGame().swapPlayer();
	}
}
