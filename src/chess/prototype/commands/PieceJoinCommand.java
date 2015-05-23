/*
 * Author: Siang Ling
 * Number: s3295217
 */
package chess.prototype.commands;

import chess.core.Piece;
import chess.mvc.models.PieceJoinEvent;
import chess.mvc.models.UpdateUIEvent;
import chess.prototype.composite.CombinePiece;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.ChessEventDispatcher;

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
		piece.setOwner(pieceSelected.getOwner());
		if(!piece.add(pieceSelected)) return;
		if(!piece.add(pieceTarget)) return;
		
		this.getBoard().setPiece(newPos, piece);
		this.getBoard().setPiece(oldPos, null);
		
		piece.getOwner().increaseMove();
		this.getGame().swapPlayer();
		ChessEventDispatcher.getInstance().fireEvent(new UpdateUIEvent());
	}
}
